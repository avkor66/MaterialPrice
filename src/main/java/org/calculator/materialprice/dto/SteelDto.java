package org.calculator.materialprice.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class SteelDto {
    private List<String> steelGrade; // Массив марок
    private String description;
    private List<String> substitutes; // Массив заменителей
    private String weldability;
    private String application;
    private Double density;
    private Set<UUID> washerStandardIds;
}

