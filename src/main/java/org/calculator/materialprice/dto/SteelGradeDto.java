package org.calculator.materialprice.dto;

import java.util.Set;
import java.util.UUID;

public class SteelGradeDto {
    private UUID id;
    private String steel_grade;

    private Set<WasherStandardDto> washerStandardIds;
}
