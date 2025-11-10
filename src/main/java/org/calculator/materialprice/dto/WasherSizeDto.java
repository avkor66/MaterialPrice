package org.calculator.materialprice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class WasherSizeDto {
    private UUID id;
    private String size;
    private BigDecimal nominal_thread_diameter;
    private BigDecimal inner_diameter;
    private BigDecimal outer_diameter;
    private BigDecimal thickness;
    private BigDecimal width_b;
    private String purpose;
}
