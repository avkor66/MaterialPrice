package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.CatalogNutSizes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NutSizeRepository extends JpaRepository<CatalogNutSizes, UUID> {
    CatalogNutSizes findBySize(String size);
}
