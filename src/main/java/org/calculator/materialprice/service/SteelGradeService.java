package org.calculator.materialprice.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.calculator.materialprice.domain.SteelGrades;
import org.calculator.materialprice.domain.CatalogWasherStandards;
import org.calculator.materialprice.dto.SteelGradeDto;
import org.calculator.materialprice.repository.SteelGradeRepository;
import org.calculator.materialprice.repository.WasherStandardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public SteelGrades createSteelGrade(SteelGrades steelGrades) {
        return repository.save(steelGrades);
    }

    public SteelGrades findSteelGradeById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public SteelGrades getSteelGrade(String steelGrade) {
        return repository.findBySteelGradeName(steelGrade);
    }

    public List<SteelGradeDto> getSteelGrades() {
        return repository.findAll().stream()
                .map(SteelGradeDto::new)
                .collect(Collectors.toList());
    }

    public List<SteelGradeDto> getWasherSteelGrades() {
        return repository.findAllDependentWashers().stream()
                .map(SteelGradeDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public SteelGrades linkStandardToSteelGrade(UUID steelGradeId, UUID standardId) {

        SteelGrades steelGrade = steelGradeRepository.findById(steelGradeId)
                .orElseThrow(() -> new EntityNotFoundException("Марка стали не найдена."));

        CatalogWasherStandards standard = washerStandardRepository.findById(standardId)
                .orElseThrow(() -> new EntityNotFoundException("Стандарт не найден."));

        steelGrade.getWashers().add(standard);

        standard.getGrades().add(steelGrade);

        return steelGradeRepository.save(steelGrade);
    }



}
