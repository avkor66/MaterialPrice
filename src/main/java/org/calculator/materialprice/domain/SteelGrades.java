package org.calculator.materialprice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(exclude = "standards")
@Table(name = "steel_grades")
public class SteelGrades {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "steel_grade_name", nullable = false)
    private String steelGradeName;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "substitutes")
    private String substitutes;

    @Column(name = "weldability")
    private String weldability;

    @Column(name = "application", length = 1024)
    private String application;

    @Column(name = "density")
    private Double density;

    @ManyToOne
    @JoinColumn(name = "standard_id")
    private SteelStandard standard;

    @ManyToMany
    @JoinTable(
            name = "washer_grade",
            joinColumns = {@JoinColumn(name = "grade_id")},
            inverseJoinColumns = {@JoinColumn(name = "washer_id")}
    )
    private Set<CatalogWasherStandards> washers = new HashSet<>();

    public SteelGrades(String steelGradeName) {
        this.steelGradeName = steelGradeName;
    }

    public SteelGrades(
            String steelGradeName,
            String description,
            String substitutes,
            String weldability,
            String application,
            double density) {
        this.steelGradeName = steelGradeName;
        this.description = description;
        this.substitutes = substitutes;
        this.weldability = weldability;
        this.application = application;
        this.density = density;
    }
}



//
// //Главная таблица Standard
//@ManyToMany
//@JoinTable(
//        name = "grade_standard",
//        joinColumns = {@JoinColumn(name = "standard_id")},
//        inverseJoinColumns = {@JoinColumn(name = "grade_id")}
//)
//private Set<Grades> grades = new HashSet<>();
//
//
//
//
//
// //Зависимая таблица Grade
//
//@ManyToMany
//@JoinTable(
//        name = "grade_standard",
//        joinColumns = {@JoinColumn(name = "grade_id")},
//        inverseJoinColumns = {@JoinColumn(name = "standard_id")}
//)
//private Set<Standard> standards = new HashSet<>();
//
//
//
//
//
//
// //Зависимая таблица Grade
//
//@ManyToOne
//@JoinColumn(name = "standard_id")
//private Standard standard;
//
//
// //Главная таблица Standard
//
//@OneToMany(mappedBy = "standard")   // Можно добавить каскад (mappedBy = "standard", cascade = CascadeType.ALL) для удаления зависимых полей от главной таблицы
//private Set<Grade> grades = new HashSet<>();
//
// //Более логичная запись - в стандарт добавлять марки стали
//public void addGradeToStandard(Grade grade) {
//
//    grades.add(grade);
//    grade.setStandard(this);
//}
//
