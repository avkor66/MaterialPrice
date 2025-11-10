package org.calculator.materialprice.service;

import lombok.AllArgsConstructor;
import org.calculator.materialprice.domain.ProductPrice;
import org.calculator.materialprice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public void saveProductsToDatabase(MultipartFile file) {
        if(ExcelUploadService.isValidExcelFile(file)) {
            try {
                List<ProductPrice> productPrices = ExcelUploadService.getProductsDataFromExcel(file.getInputStream());
                this.productRepository.saveAll(productPrices);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    public List<ProductPrice> getProducts() {
        return this.productRepository.findAll();
    }

    public List<ProductPrice> getProductsByName(String productName) {
        return this.productRepository.findByProductName(productName);
    }
}
