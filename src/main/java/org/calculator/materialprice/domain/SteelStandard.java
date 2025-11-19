package org.calculator.materialprice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "grades")
@Table(name = "steel_standards")
public class SteelStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "file_name")
    private String file;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "standard", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SteelGrades> grades = new HashSet<>();

    public SteelStandard(String name, String title, String link, String file, String image) {
        this.name = name;
        this.title = title;
        this.link = link;
        this.file = file;
        this.image = image;
    }

    public void addGradeToStandard(SteelGrades grade) {
        this.grades.add(grade);
        grade.setStandard(this);
    }

}
