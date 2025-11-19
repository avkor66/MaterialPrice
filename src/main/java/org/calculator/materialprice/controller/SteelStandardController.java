package org.calculator.materialprice.controller;

import lombok.RequiredArgsConstructor;
import org.calculator.materialprice.domain.SteelStandard;
import org.calculator.materialprice.dto.SteelStandardDto;
import org.calculator.materialprice.service.SteelStandardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/steel-standards")
@RequiredArgsConstructor
public class SteelStandardController {

    private final SteelStandardService steelStandardService;

    @PostMapping
    public ResponseEntity<SteelStandard> saveSteelStandard(
            @RequestBody SteelStandardDto steelStandardDto) {

        try {
            SteelStandard savedStandard = steelStandardService.saveOrUpdateStandard(steelStandardDto);

            return new ResponseEntity<>(savedStandard, HttpStatus.CREATED);

        } catch (Exception e) {
            System.err.println("Error saving steel standard: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/{id}") для получения ГОСТа по ID
    // @GetMapping для получения всего списка ГОСТов
    // @DeleteMapping("/{id}") для удаления ГОСТа
}