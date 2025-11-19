package org.calculator.materialprice.controller;

import lombok.RequiredArgsConstructor;
import org.calculator.materialprice.domain.SteelStandard;
import org.calculator.materialprice.dto.SteelGradeDto;
import org.calculator.materialprice.dto.SteelStandardDto;
import org.calculator.materialprice.service.SteelGradeService;
import org.calculator.materialprice.service.SteelStandardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("steel")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SteelController {

    private final SteelStandardService steelStandardService;
    private final SteelGradeService steelGradeService;

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

    @GetMapping("standard")
    public ResponseEntity<List<SteelStandardDto>> getAllSteelStandard() {
        return new ResponseEntity<>(steelStandardService.getSteelStandard(), HttpStatus.OK);
    }

    @GetMapping("grade")
    public ResponseEntity<List<SteelGradeDto>> getAllSteelGrade() {
        return new ResponseEntity<>(steelGradeService.getSteelGrades() ,HttpStatus.OK);
    }



    // @GetMapping("/{id}") для получения ГОСТа по ID
    // @GetMapping для получения всего списка ГОСТов
    // @DeleteMapping("/{id}") для удаления ГОСТа
}