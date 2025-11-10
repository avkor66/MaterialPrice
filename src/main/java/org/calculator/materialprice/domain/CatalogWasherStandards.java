package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"washerSize", "steelGrade"})
@EqualsAndHashCode(exclude = {"washerSize", "steelGrade"})@AllArgsConstructor
@NoArgsConstructor
@Table(name = "washer_standard")
public class CatalogWasherStandards {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String standard;
    private String title;
    private String description;
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "washerStandardSize", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CatalogWasherSizes> washerSize = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "washerStandard")
    private Set<CatalogSteelGrades> steelGrade = new HashSet<>();
}
