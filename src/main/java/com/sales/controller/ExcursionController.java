package com.sales.controller;

import com.sales.service.ExcursionService;
import com.sales.service.dto.ExcursionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/excursion")
public class ExcursionController {

    private final ExcursionService excursionService;

    @Autowired
    public ExcursionController(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }


    @GetMapping
    public ResponseEntity<List<ExcursionDto>> getAllExcursions() {
        List<ExcursionDto> excursions = excursionService.getAllExcursions();
        return new ResponseEntity<>(excursions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExcursionDto> getExcursionById(@PathVariable UUID id) {
        ExcursionDto excursionDTO = excursionService.getExcursionById(id);
        if (excursionDTO != null) {
            return new ResponseEntity<>(excursionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ExcursionDto> createExcursion(@RequestBody ExcursionDto excursionDTO) {
        ExcursionDto createdExcursionDto = excursionService.createExcursion(excursionDTO);
        return new ResponseEntity<>(createdExcursionDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExcursionDto> updateExcursion(@PathVariable UUID id, @RequestBody ExcursionDto excursionDTO) {
        ExcursionDto updatedExcursionDto = excursionService.updateExcursion(id, excursionDTO);
        if (updatedExcursionDto != null) {
            return new ResponseEntity<>(updatedExcursionDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcursion(@PathVariable UUID id) {
        excursionService.deleteExcursion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}