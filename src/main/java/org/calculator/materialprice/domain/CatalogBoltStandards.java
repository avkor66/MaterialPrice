package org.calculator.materialprice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bolt_standard")
public class CatalogBoltStandards {
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
            name = "bolt_grade",
            joinColumns = {@JoinColumn(name = "bolt_id")},
            inverseJoinColumns = {@JoinColumn(name = "grade_id")}
    )
    private Set<SteelGrades> grades = new HashSet<>();

    @OneToMany(mappedBy = "boltStandard", cascade = CascadeType.ALL)
    @Column(name = "bolt_sizes")
    private Set<CatalogBoltSizes> boltSizes = new HashSet<>();

    public CatalogBoltStandards(String standard, String title, String description, String type, String link, String file, String image) {
        this.standard = standard;
        this.title = title;
        this.description = description;
        this.type = type;
        this.link = link;
        this.file = file;
        this.image = image;
    }
    public void addBoltSizeToStandard(CatalogBoltSizes boltSize) {
        this.boltSizes.add(boltSize);
        boltSize.setBoltStandard(this);
    }
    public void addGradeToStandard(SteelGrades grade) {
        this.grades.add(grade);
    }
}
