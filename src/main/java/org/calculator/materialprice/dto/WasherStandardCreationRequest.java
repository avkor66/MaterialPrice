package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WasherStandardCreationRequest {
    private String standard;
    private String title;
    private String description;
    private String type;
}