package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByProductNameContainingIgnoreCase(String search, Pageable pageable);
}
