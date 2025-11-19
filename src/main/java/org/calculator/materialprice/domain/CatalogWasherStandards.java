package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
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

    @Column(name = "link")
    private String link;

    @Column(name = "file_name")
    private String file;

    @Column(name = "image")
    private String image;

    @ManyToMany
    @JoinTable(
            name = "washer_grade",
            joinColumns = {@JoinColumn(name = "washer_id")},
            inverseJoinColumns = {@JoinColumn(name = "grade_id")}
    )
    private Set<SteelGrades> grades = new HashSet<>();

    @OneToMany(mappedBy = "washerStandard", cascade = CascadeType.ALL)
    @Column(name = "washer_sizes")
    private Set<CatalogWasherSizes> washerSizes = new HashSet<>();

    public CatalogWasherStandards(String standard, String title, String description, String type, String link, String file, String image) {
        this.standard = standard;
        this.title = title;
        this.description = description;
        this.type = type;
        this.link = link;
        this.file = file;
        this.image = image;
    }
    public void addWasherSizeToStandard(CatalogWasherSizes washerSize) {
        this.washerSizes.add(washerSize);
        washerSize.setWasherStandard(this);
    }
    public void addGradeToStandard(SteelGrades grade) {
        this.grades.add(grade);
    }
}
