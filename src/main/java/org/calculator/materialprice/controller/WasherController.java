package org.calculator.materialprice.controller;

import org.calculator.materialprice.domain.CatalogSteelGrades;
import org.calculator.materialprice.domain.CatalogWasherSizes;
import org.calculator.materialprice.domain.CatalogWasherStandards;
import org.calculator.materialprice.dto.SteelGradeCreationRequest;
import org.calculator.materialprice.dto.WasherSizeCreationRequest;
import org.calculator.materialprice.dto.WasherStandardCreationRequest;
import org.calculator.materialprice.dto.WasherSteelRequest;
import org.calculator.materialprice.service.SteelGradeService;
import org.calculator.materialprice.service.WasherSizeService;
import org.calculator.materialprice.service.WasherStandardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/details")
public class WasherController {

    private final SteelGradeService steelGradeService;
    private final WasherSizeService washerSizeService;
    private final WasherStandardService washerStandardService;




    public WasherController(
            SteelGradeService steelGradeService,
            WasherSizeService washerSizeService,
            WasherStandardService washerStandardService
    ){

        this.steelGradeService = steelGradeService;
        this.washerSizeService = washerSizeService;
        this.washerStandardService = washerStandardService;
    }

    @PostMapping("/washer-standard")
    public ResponseEntity<CatalogWasherStandards> createWasherStandard(
            @RequestBody WasherStandardCreationRequest request
    ) {
        CatalogWasherStandards catalogWasherStandards = new CatalogWasherStandards();
        catalogWasherStandards.setStandard(request.getStandard());
        catalogWasherStandards.setDescription(request.getDescription());
        catalogWasherStandards.setType(request.getType());
        catalogWasherStandards.setTitle(request.getTitle());

        CatalogWasherStandards savedResult = washerStandardService.createWasherStandard(catalogWasherStandards);
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    @PostMapping("/washer-size")
    public ResponseEntity<CatalogWasherSizes> createWasherSize(
            @RequestBody WasherSizeCreationRequest request
    ) {
        CatalogWasherStandards standard = washerStandardService.findWasherStandardById(request.getWasher_standard_id());
        CatalogWasherSizes washerSizes = new CatalogWasherSizes();
        washerSizes.setSize(request.getSize());
        washerSizes.setNominal_thread_diameter(request.getNominal_thread_diameter());
        washerSizes.setInner_diameter(request.getInner_diameter());
        washerSizes.setOuter_diameter(request.getOuter_diameter());
        washerSizes.setThickness(request.getThickness());
        washerSizes.setWidth_b(request.getWidth_b());
        washerSizes.setPurpose(request.getPurpose());
        washerSizes.setWasherStandardSize(standard);

        CatalogWasherSizes savedResult = washerSizeService.createWasherSize(washerSizes);
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    @PostMapping("/steel-grade")
    public ResponseEntity<CatalogSteelGrades> createWasherSize(
            @RequestBody SteelGradeCreationRequest request
    ) {
        CatalogSteelGrades steelGrade = new CatalogSteelGrades();
        steelGrade.setSteelGrade(request.getSteel_grade());
        CatalogSteelGrades savedResult = steelGradeService.createSteelGrade(steelGrade);

        steelGradeService.linkStandardToSteelGrade(savedResult.getId(), request.getWasher_standard_id());

        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    @PostMapping("/washer-steel")
    public ResponseEntity<CatalogSteelGrades> washerJoinSteel(
            @RequestBody WasherSteelRequest request
    ) {
        return new ResponseEntity<>(
                steelGradeService.linkStandardToSteelGrade(request.getSteel_grade_id(),
                        request.getWasher_standard_id()), HttpStatus.CREATED
        );
    }
}
