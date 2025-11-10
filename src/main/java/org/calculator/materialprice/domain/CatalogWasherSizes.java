package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude = {"washerStandardSize"}) // Исключаем поле, которое может быть LAZY
@EqualsAndHashCode(exclude = {"washerStandardSize"}) // Исключаем поле, которое может быть LAZY
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "washer_standard_id", nullable = false)

    private CatalogWasherStandards washerStandardSize;
}