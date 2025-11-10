package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "steel_grade")
public class CatalogSteelGrades {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String steelGrade;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "washer_steel",
            joinColumns = @JoinColumn(name = "steel_grade_id"),
            inverseJoinColumns = @JoinColumn(name = "washer_standard_id")
    )
    private Set<CatalogWasherStandards> washerStandard = new HashSet<>();
}
