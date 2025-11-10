package org.calculator.materialprice.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class WasherStandardDto {
    private UUID id;
    private String standard;
    private String title;
    private String description;
    private String type;

    private List<WasherSizeDto> washerSizes;

    private Set<SteelGradeDto> steelGradeIds;
}
