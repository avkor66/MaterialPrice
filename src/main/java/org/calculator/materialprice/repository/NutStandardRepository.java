package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.CatalogNutStandards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NutStandardRepository extends JpaRepository<CatalogNutStandards, UUID> {
    CatalogNutStandards findByStandard(String standard);
}
