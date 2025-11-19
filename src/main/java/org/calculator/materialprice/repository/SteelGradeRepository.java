package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.SteelGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SteelGradeRepository extends JpaRepository<SteelGrades, UUID> {
    SteelGrades findBySteelGradeName(String steelGrade);

    @Query("SELECT DISTINCT sg FROM SteelGrades sg JOIN sg.washers")
    List<SteelGrades> findAllDependentWashers();
}
