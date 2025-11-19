package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.ProductPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductPrice, UUID> {
    List<ProductPrice> findByProductName(String productName);

    Page<ProductPrice> findAll(Pageable pageable);

    Page<ProductPrice> findByProductNameContainingIgnoreCase(String search, Pageable pageable);

    List<ProductPrice> findTopByProductNameAndDimensionsAndSteelGradeAndParameterOrderByPriceAsc(
            String productName,
            String dimensions,
            String steelGrade,
            String parameter
    );
}
