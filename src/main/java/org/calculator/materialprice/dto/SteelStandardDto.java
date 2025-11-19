package org.calculator.materialprice.dto;

import lombok.Data;
import org.calculator.materialprice.domain.SteelStandard;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SteelStandardDto {
    private String name;
    private String title;
    private String link;
    private String file;
    private String image;
    private Set<SteelGradeDto> grades;

    public SteelStandardDto(SteelStandard entity) {
        this.name = entity.getName();
        this.title = entity.getTitle();
        this.link = entity.getLink();
        this.file = entity.getFile();
        this.image = entity.getImage();

        this.grades = entity.getGrades().stream()
                .map(SteelGradeDto::new)
                .collect(Collectors.toSet());
    }
}

