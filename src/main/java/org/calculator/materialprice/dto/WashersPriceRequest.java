package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WashersPriceRequest {
    private String species;
    private String diameter;
    private String steelGrade;
    private Number quantity;
    private Number sawCut;
    private String standard;
}
