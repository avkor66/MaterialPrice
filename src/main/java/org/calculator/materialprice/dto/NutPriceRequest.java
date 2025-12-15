package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutPriceRequest {
    private String species;
    private String diameter;
    private String steelGrade;
    private Number height;
    private Number quantity;
    private Number sawCut;
    private String stateStandard;
}
