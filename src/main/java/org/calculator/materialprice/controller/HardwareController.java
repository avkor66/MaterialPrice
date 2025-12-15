package org.calculator.materialprice.controller;

import org.calculator.materialprice.dto.BoltPriceRequest;
import org.calculator.materialprice.dto.NutPriceRequest;
import org.calculator.materialprice.dto.WashersPriceRequest;
import org.calculator.materialprice.service.ProcessingBoltService;
import org.calculator.materialprice.service.ProcessingNutService;
import org.calculator.materialprice.service.ProcessingWasherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hardware")
public class HardwareController {

    ProcessingWasherService processingWasherService;
    ProcessingBoltService processingBoltService;
    ProcessingNutService processingNutService;

    public HardwareController(
            ProcessingWasherService processingWasherService,
            ProcessingBoltService processingBoltService,
            ProcessingNutService processingNutService
    ) {
        this.processingWasherService = processingWasherService;
        this.processingBoltService = processingBoltService;
        this.processingNutService = processingNutService;
    }

    @PostMapping("/washers/price")
    public ResponseEntity<?> updateProductsData(@RequestBody WashersPriceRequest request) {

        System.out.println("request washer price");
        System.out.println(request.getStateStandard());
        System.out.println(request.getDiameter());
        System.out.println(request.getSteelGrade());
        System.out.println(request.getQuantity());
        System.out.println(request.getSpecies());
        System.out.println(request.getSawCut());


        return ResponseEntity.ok(this.processingWasherService.getProceedData(request));
    }

    @PostMapping("/bolt/price")
    public ResponseEntity<?> updateProductsData(@RequestBody BoltPriceRequest request) {

        System.out.println("request bolt price");
        System.out.println(request.getStateStandard());
        System.out.println(request.getDiameter());
        System.out.println(request.getSteelGrade());
        System.out.println(request.getQuantity());
        System.out.println(request.getSpecies());
        System.out.println(request.getSawCut());


        return ResponseEntity.ok(this.processingBoltService.getProceedData(request));

    }

    @PostMapping("/nut/price")
    public ResponseEntity<?> updateProductsData(@RequestBody NutPriceRequest request) {

        System.out.println("request bolt price");
        System.out.println(request.getStateStandard());
        System.out.println(request.getDiameter());
        System.out.println(request.getSteelGrade());
        System.out.println(request.getQuantity());
        System.out.println(request.getSpecies());
        System.out.println(request.getSawCut());


        return ResponseEntity.ok(this.processingNutService.getProceedData(request));

    }
}
