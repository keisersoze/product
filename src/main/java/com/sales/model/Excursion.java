package com.sales.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "excursion")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private LocalDate tourDate;

    @Column
    private String language;

    @Column
    private String tourCode;

}
