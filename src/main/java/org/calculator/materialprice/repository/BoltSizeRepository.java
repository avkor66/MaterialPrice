package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.CatalogBoltSizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoltSizeRepository extends JpaRepository<CatalogBoltSizes, UUID> {
    CatalogBoltSizes findBySize(String size);
}
