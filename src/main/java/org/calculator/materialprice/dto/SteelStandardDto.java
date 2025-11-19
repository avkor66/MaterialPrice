package org.calculator.materialprice.dto;

import lombok.Data;

import java.util.List;

@Data
public class SteelStandardDto {
    private String name;
    private String title;
    private String link;
    private String file;
    private String image;
    private List<SteelDto> grades;
}
