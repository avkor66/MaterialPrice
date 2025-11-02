package org.calculator.materialprice.repository;

import org.calculator.materialprice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductName(String productName);
}
