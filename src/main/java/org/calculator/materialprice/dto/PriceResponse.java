package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceResponse {
    private String species;
    private String steelGrade;
    private Boolean isSteelGradeAllowed;
    private BigDecimal pricePerKg;
    private BigDecimal blankWeightKg;
    private BigDecimal blankLengthMM;
    private BigDecimal blankWeightPerMM;
    private BigDecimal totalPrice;
}
