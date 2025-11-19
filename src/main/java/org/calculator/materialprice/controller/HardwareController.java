package org.calculator.materialprice.controller;

import org.calculator.materialprice.dto.WashersPriceRequest;
import org.calculator.materialprice.service.ProcessingWasherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hardware/washers")
public class HardwareController {

    ProcessingWasherService processingWasherService;

    public HardwareController(ProcessingWasherService processingWasherService) {
        this.processingWasherService = processingWasherService;
    }

    @PostMapping("/price")
    public ResponseEntity<?> updateProductsData(@RequestBody WashersPriceRequest request) {

        return ResponseEntity.ok(this.processingWasherService.getProceedData(request));
    }
}
