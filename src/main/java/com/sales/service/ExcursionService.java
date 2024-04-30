package com.sales.service;

import com.sales.model.Excursion;
import com.sales.repository.ExcursionRepository;
import com.sales.service.dto.ExcursionDto;
import com.sales.service.dto.ExcursionNoIdDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExcursionService {

    private final ExcursionRepository excursionRepository;

    @Autowired
    public ExcursionService(ExcursionRepository excursionRepository) {
        this.excursionRepository = excursionRepository;
    }

    @Transactional
    public List<ExcursionDto> getAllExcursions() {
        List<Excursion> excursions = excursionRepository.findAll();
        return excursions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExcursionDto getExcursionById(UUID id) {
        Optional<Excursion> optionalExcursion = excursionRepository.findById(id);
        return optionalExcursion.map(this::convertToDto).orElse(null);
    }

    @Transactional
    public ExcursionDto createExcursion(ExcursionNoIdDto excursionDTO) {
        Excursion excursion = convertToEntity(excursionDTO);
        Excursion createdExcursion = excursionRepository.save(excursion);
        return convertToDto(createdExcursion);
    }


    @Transactional
    public ExcursionDto updateExcursion(UUID id, ExcursionNoIdDto excursionDTO) {
        if (excursionRepository.existsById(id)) {
            Excursion excursion = convertToEntity(excursionDTO);
            excursion.setId(id);
            Excursion updatedExcursion = excursionRepository.save(excursion);
            return convertToDto(updatedExcursion);
        } else {
            return null;
        }
    }

    public void deleteExcursion(UUID id) {
        excursionRepository.deleteById(id);
    }

    private ExcursionDto convertToDto(Excursion staffMember) {
        ExcursionDto staffMemberDto = new ExcursionDto();
        BeanUtils.copyProperties(staffMember, staffMemberDto);
        return staffMemberDto;
    }

    private Excursion convertToEntity(ExcursionDto staffMemberDto) {
        Excursion staffMember = new Excursion();
        BeanUtils.copyProperties(staffMemberDto, staffMember);
        return staffMember;
    }

    private Excursion convertToEntity(ExcursionNoIdDto staffMemberDto) {
        Excursion staffMember = new Excursion();
        BeanUtils.copyProperties(staffMemberDto, staffMember);
        return staffMember;
    }

}
