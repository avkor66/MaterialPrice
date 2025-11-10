package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.CatalogWasherStandards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WasherStandardRepository extends JpaRepository<CatalogWasherStandards, UUID> {
    CatalogWasherStandards findByStandard(String standard);

}
