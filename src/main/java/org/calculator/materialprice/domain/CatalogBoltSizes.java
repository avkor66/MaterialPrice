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
@Table(name = "bolt_size")
public class CatalogBoltSizes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String size;
    private BigDecimal thread_pitch;
    private BigDecimal spanner_size;
    private BigDecimal head_height;
    private Number range_min;
    private Number range_max;
    private Number step;
    private String designation;

    @ManyToOne
    @JoinColumn(name = "bolt_standard_id")
    private CatalogBoltStandards boltStandard;

    public CatalogBoltSizes(String size, Double thread_pitch, Double spanner_size, Double head_height, Number range_min, Number range_max, Number step, String designation
    ) {
        this.size = size;
        this.thread_pitch = BigDecimal.valueOf(thread_pitch);
        this.spanner_size = BigDecimal.valueOf(spanner_size);
        this.head_height = BigDecimal.valueOf(head_height);
        this.range_min = range_min;
        this.range_max = range_max;
        this.step = step;
        this.designation = designation;
    };

}
