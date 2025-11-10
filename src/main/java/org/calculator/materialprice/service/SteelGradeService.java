package org.calculator.materialprice.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.calculator.materialprice.domain.CatalogSteelGrades;
import org.calculator.materialprice.domain.CatalogWasherStandards;
import org.calculator.materialprice.repository.SteelGradeRepository;
import org.calculator.materialprice.repository.WasherStandardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SteelGradeService {

    private final SteelGradeRepository repository;
    private final SteelGradeRepository steelGradeRepository;
    private final WasherStandardRepository washerStandardRepository;

    public SteelGradeService(SteelGradeRepository repository, SteelGradeRepository steelGradeRepository, WasherStandardRepository washerStandardRepository) {
        this.repository = repository;
        this.steelGradeRepository = steelGradeRepository;
        this.washerStandardRepository = washerStandardRepository;
    }

    public CatalogSteelGrades createSteelGrade(CatalogSteelGrades catalogSteelGrades) {
        return repository.save(catalogSteelGrades);
    }

    public CatalogSteelGrades findSteelGradeById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public CatalogSteelGrades getSteelGrade(String steelGrade) {
        return repository.findBySteelGrade(steelGrade);
    }

    public List<CatalogSteelGrades> getAllSteelGrades() {
        return repository.findAll();
    }

    @Transactional
    public CatalogSteelGrades linkStandardToSteelGrade(UUID steelGradeId, UUID standardId) {

        CatalogSteelGrades steelGrade = steelGradeRepository.findById(steelGradeId)
                .orElseThrow(() -> new EntityNotFoundException("Марка стали не найдена."));

        CatalogWasherStandards standard = washerStandardRepository.findById(standardId)
                .orElseThrow(() -> new EntityNotFoundException("Стандарт не найден."));

        steelGrade.getWasherStandard().add(standard);

        standard.getSteelGrade().add(steelGrade);

        return steelGradeRepository.save(steelGrade);
    }

}
