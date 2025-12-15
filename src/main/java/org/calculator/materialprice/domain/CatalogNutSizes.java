package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "nut_size")
public class CatalogNutSizes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String size;
    private BigDecimal thread_pitch;
    private BigDecimal spanner_size;
    private BigDecimal height;
    private Number outerCupDiameter;
    private String designation;

    @ManyToOne
    @JoinColumn(name = "nut_standard_id")
    private CatalogNutStandards nutStandard;

    public CatalogNutSizes(String size, Double thread_pitch, Double spanner_size, Double height, Number outerCupDiameter, String designation
    ) {
        this.size = size;
        this.thread_pitch = BigDecimal.valueOf(thread_pitch);
        this.spanner_size = BigDecimal.valueOf(spanner_size);
        this.height = BigDecimal.valueOf(height);
        this.outerCupDiameter = outerCupDiameter;
        this.designation = designation;
    };
}
