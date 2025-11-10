package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.CatalogSteelGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SteelGradeRepository extends JpaRepository<CatalogSteelGrades, UUID> {
    CatalogSteelGrades findBySteelGrade(String steelGrade);
}
