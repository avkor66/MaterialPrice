package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WashersPriceRequest {
    private String species;
    private Number outerDiameter;
    private String diameter;
    private String steelGrade;
    private Number quantity;
}
