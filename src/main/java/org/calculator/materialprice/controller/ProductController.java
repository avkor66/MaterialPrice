package org.calculator.materialprice.controller;

import org.calculator.materialprice.domain.ProductPrice;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductPrice>> getAllProducts(
            Pageable pageable,
            @RequestParam(required = false) String search) {

        Page<ProductPrice> page;

        if (search != null && !search.isEmpty()) {
            page = productRepository.findByProductNameContainingIgnoreCase(search, pageable);
        } else {
            page = productRepository.findAll(pageable);
        }

        return ResponseEntity.ok(page);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductPrice>> getProductsByProductName(@RequestParam(value = "name") String productName) {
        return new ResponseEntity<>(productService.getProductsByName(productName), HttpStatus.OK);
    }

    @PostMapping("/upload-product-data")
    public ResponseEntity<?> updateProductsData(@RequestParam("file") MultipartFile file) {
        this.productService.saveProductsToDatabase(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Products data uploaded and saved to database successfully"));
    }
}
