package org.calculator.materialprice.controller;

import lombok.AllArgsConstructor;
import org.calculator.materialprice.dto.WashersPriceRequest;
import org.calculator.materialprice.dto.WashersPriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/hardware/washers")
public class HardwareController {

    @PostMapping("/price")
    public ResponseEntity<?> updateProductsData(@RequestBody WashersPriceRequest request) {
        System.out.println(request.toString());
        WashersPriceResponse response = new WashersPriceResponse();
        response.setBlankLengthKg(10.0);
        response.setBlankWeightKg(20.0);
        response.setPricePerKg(125.0);
        response.setSpecies("шайба");
        response.setTotalPrice(2960.0);
        response.setSteelGrade("ст3");
        return ResponseEntity.ok(response);
    }
}
