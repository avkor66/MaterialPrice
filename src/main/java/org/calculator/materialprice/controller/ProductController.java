package org.calculator.materialprice.controller;

import lombok.AllArgsConstructor;
import org.calculator.materialprice.domain.Product;
import org.calculator.materialprice.repository.ProductRepository;
import org.calculator.materialprice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductsByProductName(@RequestParam(value = "name") String productName) {
        return new ResponseEntity<>(productService.getProductsByName(productName), HttpStatus.OK);
    }

    @PostMapping("/upload-product-data")
    public ResponseEntity<?> updateProductsData(@RequestParam("file") MultipartFile file) {
        this.productService.saveProductsToDatabase(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Products data uploaded and saved to database successfully"));
    }
}
