package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.CatalogWasherSizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WasherSizeRepository extends JpaRepository<CatalogWasherSizes, UUID> {

    CatalogWasherSizes findBySize(String size);
}
