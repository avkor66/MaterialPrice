package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SteelGradeCreationRequest {
    private String steel_grade;
    private UUID washer_standard_id;
}
