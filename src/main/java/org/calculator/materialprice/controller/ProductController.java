package org.calculator.materialprice.controller;

import lombok.AllArgsConstructor;
import org.calculator.materialprice.domain.Product;
import org.calculator.materialprice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.FOUND);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductsByProductName(@RequestParam(value = "name") String productName) {
        return new ResponseEntity<>(productService.getProductsByName(productName), HttpStatus.FOUND);
    }

    @PostMapping("/upload-product-data")
    public ResponseEntity<?> updateProductsData(@RequestParam("file") MultipartFile file) {
        this.productService.saveProductsToDatabase(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Products data uploaded and saved to database successfully"));
    }
}
