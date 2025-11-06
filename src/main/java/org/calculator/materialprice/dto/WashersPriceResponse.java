package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WashersPriceResponse {
    private String species;
    private String steelGrade;
    private Double pricePerKg;
    private Double blankWeightKg;
    private Double blankLengthKg;
    private Double totalPrice;
}
