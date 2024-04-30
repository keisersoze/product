package com.sales.service.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExcursionDto extends ExcursionNoIdDto {

    private UUID id;
}
