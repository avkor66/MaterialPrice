package org.calculator.materialprice.dto;

import lombok.Data;
import org.calculator.materialprice.domain.CatalogWasherStandards;
import org.calculator.materialprice.domain.SteelStandard;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class WasherStandardDto {

    private String standard;
    private String title;
    private String description;
    private String type;
    private String link;
    private String file;
    private String image;

    private Set<WasherSizeDto> washerSizes;
    private Set<SteelGradeDto> steelGrades;

    public WasherStandardDto(CatalogWasherStandards entity) {
        this.standard = entity.getStandard();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.type = entity.getType();
        this.link = entity.getLink();
        this.file = entity.getFile();
        this.image = entity.getImage();

        this.washerSizes = entity.getWasherSizes().stream()
                .map(WasherSizeDto::new)
                .collect(Collectors.toSet());
        this.steelGrades = entity.getGrades().stream()
                .map(SteelGradeDto::new)
                .collect(Collectors.toSet());
    }
}
