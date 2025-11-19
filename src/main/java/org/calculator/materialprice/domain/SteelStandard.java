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

//    @ManyToMany
//    @JoinTable(
//            name = "grade_standard",
//            joinColumns = {@JoinColumn(name = "standard_id")},
//            inverseJoinColumns = {@JoinColumn(name = "grade_id")}
//    )
//    private Set<SteelGrades> grades = new HashSet<>();

    public SteelStandard(String name, String title, String link, String file, String image) {
        this.name = name;
        this.title = title;
        this.link = link;
        this.file = file;
        this.image = image;
    }


    @OneToMany(mappedBy = "standard", cascade = CascadeType.ALL)
    private Set<SteelGrades> grades = new HashSet<>();

    public void addGradeToStandard(SteelGrades grade) {
        this.grades.add(grade);
        grade.setStandard(this);
    }



//    // Связь One-to-Many с марками стали
//    // Один ГОСТ может содержать много марок
//    @JsonIgnore
//    @OneToMany(mappedBy = "steelStandard", cascade = CascadeType.ALL, orphanRemoval = true)
//
//    private List<SteelGrades> grades = new ArrayList<>(); // Инициализируем
//
//    /**
//     * Обновляет коллекцию марок стали, очищая старые и добавляя новые элементы,
//     * сохраняя при этом ссылку на существующий объект-коллекцию JPA.
//     */
//    public void setGrades(List<SteelGrades> newGrades) {
//        this.grades.clear(); // 1. Удаляем все текущие элементы (Hibernate пометит их на удаление)
//        if (newGrades != null) {
//            newGrades.forEach(grade -> {
//                this.grades.add(grade); // 2. Добавляем новые элементы в ту же коллекцию
//                grade.setSteelStandard(this); // 3. Устанавливаем обратную связь
//            });
//        }
//    }
}
