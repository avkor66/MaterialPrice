package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "washer_size")
public class CatalogWasherSizes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String size;
    private BigDecimal nominal_thread_diameter;
    private BigDecimal inner_diameter;
    private BigDecimal outer_diameter;
    private BigDecimal thickness;
    private BigDecimal width_b;
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "washer_standard_id")
    private CatalogWasherStandards washerStandard;

    public CatalogWasherSizes(String size, Double nominal_thread_diameter, Double inner_diameter, Double outer_diameter, Double width_b, Double thickness, String purpose
    ) {
        this.size = size;
        this.nominal_thread_diameter = BigDecimal.valueOf(nominal_thread_diameter);
        this.inner_diameter = BigDecimal.valueOf(inner_diameter);
        this.outer_diameter = outer_diameter == null ? null : BigDecimal.valueOf(outer_diameter);
        this.thickness = BigDecimal.valueOf(thickness);
        this.width_b = width_b == null ? null : BigDecimal.valueOf(width_b);
        this.purpose = purpose;
    };

}