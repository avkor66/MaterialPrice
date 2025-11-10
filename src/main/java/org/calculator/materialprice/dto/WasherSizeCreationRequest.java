package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class WasherSizeCreationRequest {
    private String size;
    private BigDecimal nominal_thread_diameter;
    private BigDecimal inner_diameter;
    private BigDecimal outer_diameter;
    private BigDecimal thickness;
    private BigDecimal width_b;
    private String purpose;
    private UUID washer_standard_id;
}
