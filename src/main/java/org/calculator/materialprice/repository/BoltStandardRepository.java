package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.CatalogBoltStandards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoltStandardRepository extends JpaRepository<CatalogBoltStandards, UUID> {
    CatalogBoltStandards findByStandard(String standard);
}
