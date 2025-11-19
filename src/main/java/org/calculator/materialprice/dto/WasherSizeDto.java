package org.calculator.materialprice.dto;

import lombok.Data;
import org.calculator.materialprice.domain.CatalogWasherSizes;

import java.math.BigDecimal;

@Data
public class WasherSizeDto {
    private String size;
    private BigDecimal nominal_thread_diameter;
    private BigDecimal inner_diameter;
    private BigDecimal outer_diameter;
    private BigDecimal thickness;
    private BigDecimal width_b;
    private String purpose;

    public WasherSizeDto(CatalogWasherSizes entity) {
        this.size = entity.getSize();
        this.nominal_thread_diameter = entity.getNominal_thread_diameter();
        this.inner_diameter = entity.getInner_diameter();
        this.outer_diameter = entity.getOuter_diameter();
        this.thickness = entity.getThickness();
        this.width_b = entity.getWidth_b();
        this.purpose = entity.getPurpose();
    }
}
