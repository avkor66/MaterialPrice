package org.calculator.materialprice.dto;

import lombok.Data;
import org.calculator.materialprice.domain.SteelGrades;

@Data
public class SteelGradeDto {
    private String steelGradeName;
    private String description;
    private String substitutes;
    private String weldability;
    private String application;
    private Double density;

    public SteelGradeDto(SteelGrades entity) {
        this.steelGradeName = entity.getSteelGradeName();
        this.description = entity.getDescription();
        this.substitutes = entity.getSubstitutes();
        this.weldability = entity.getWeldability();
        this.application = entity.getApplication();
        this.density = entity.getDensity();
    }
}

