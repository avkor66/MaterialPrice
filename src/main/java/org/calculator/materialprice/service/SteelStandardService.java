package org.calculator.materialprice.service;

import jakarta.transaction.Transactional;
import org.calculator.materialprice.domain.SteelGrades;
import org.calculator.materialprice.domain.SteelStandard;
import org.calculator.materialprice.dto.SteelDto;
import org.calculator.materialprice.dto.SteelStandardDto;
import org.calculator.materialprice.repository.SteelGradesRepository;
import org.calculator.materialprice.repository.SteelStandardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SteelStandardService {
    private int count = 0;
    private final SteelStandardRepository steelStandardRepository;
    private final SteelGradesRepository steelGradesRepository;

    SteelStandardService(
            SteelStandardRepository steelStandardRepository,
            SteelGradesRepository steelGradesRepository
    ){
        this.steelStandardRepository = steelStandardRepository;
        this.steelGradesRepository = steelGradesRepository;
    }

    /**
     * Конвертирует DTO марки стали в сущность SteelGrades.
     * @param dto DTO марки стали
     * @return Сущность SteelGrades
     */
    private SteelGrades convertToEntity(SteelDto dto) {
        SteelGrades entity = new SteelGrades();

        // Массив steelGrade преобразуем в строку (через запятую или первый элемент)
        // Предполагаем, что берем только первый элемент для простоты
        entity.setSteelGradeName(dto.getSteelGrade().isEmpty() ? "" : dto.getSteelGrade().get(0));

        entity.setDescription(dto.getDescription());
        // Массив substitutes преобразуем в строку
        entity.setSubstitutes(String.join(", ", dto.getSubstitutes()));

        entity.setWeldability(dto.getWeldability());
        entity.setApplication(dto.getApplication());
        entity.setDensity(dto.getDensity());

        // ВАЖНО: Связь ManyToMany (washerStandard) должна быть добавлена здесь,
        // но для этого нужен DTO и логика поиска/создания CatalogWasherStandards.
        // Пока опускаем эту логику.

        return entity;
    }

    /**
     * Сохраняет или обновляет данные о ГОСТе и связанных марках стали.
     * @param steelStandardDto DTO с данными ГОСТа и марками стали.
     * @return Сохраненная сущность StandardSteel.
     */
    @Transactional
    public SteelStandard saveOrUpdateStandard(SteelStandardDto steelStandardDto) {
        // 1. Поиск или создание сущности StandardSteel (ГОСТ)
        SteelStandard standard = steelStandardRepository.findByName(steelStandardDto.getName());

        if (standard == null) {
            standard = new SteelStandard();
            standard.setName(steelStandardDto.getName());
        }

        // Обновление общих полей ГОСТа
        standard.setLink(steelStandardDto.getLink());
        standard.setTitle(steelStandardDto.getTitle());
        standard.setImage(steelStandardDto.getImage());
        standard.setFile(steelStandardDto.getFile());

        // 2. Конвертация DTO марок стали в сущности SteelGrades
        List<SteelGrades> gradesEntities = steelStandardDto.getGrades().stream()
                .peek(val -> {
                    System.out.println("val");
                    System.out.println(count++ + " = " + val);
                })
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        // 3. Установка обратной связи и сохранение (CascadeType.ALL в StandardSteel поможет)
        // StandardSteel должен иметь метод setGrades, который устанавливает обратную связь grade.setStandardSteel(this)
//        standard.setGrades(gradesEntities);

        // 4. Сохранение родительской сущности
        return steelStandardRepository.save(standard);
    }


}
