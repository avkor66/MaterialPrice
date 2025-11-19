package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.SteelStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SteelStandardRepository extends JpaRepository<SteelStandard, UUID> {
    SteelStandard findByName(String name);
}
