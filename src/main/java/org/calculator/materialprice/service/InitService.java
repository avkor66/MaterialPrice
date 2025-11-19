package org.calculator.materialprice.service;

import jakarta.transaction.Transactional;
import org.calculator.materialprice.repository.SteelGradeRepository;
import org.calculator.materialprice.repository.SteelStandardRepository;
import org.calculator.materialprice.repository.WasherSizeRepository;
import org.calculator.materialprice.repository.WasherStandardRepository;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private final SteelStandardRepository steelStandardRepository;
    private final SteelGradeRepository steelGradeRepository;
    private final WasherStandardRepository washerStandardRepository;
    private final WasherSizeRepository washerSizeRepository;

    public InitService(
            SteelStandardRepository standardRepo,
            SteelGradeRepository gradesRepo,
            WasherSizeRepository washerSizeRepository,
            WasherStandardRepository washerStandardRepository
    ) {
        this.steelStandardRepository = standardRepo;
        this.steelGradeRepository = gradesRepo;
        this.washerStandardRepository = washerStandardRepository;
        this.washerSizeRepository = washerSizeRepository;
    }

    @Transactional
    public void createInitialData() {

        if (steelStandardRepository.count() > 1) {
            System.out.println("Данные уже записаны. Пропуск инициализации.");
            return;
        }


        System.out.println("Инициализация данных внутри транзакции.");

        System.out.println("Начальные данные успешно загружены.");
    }
}

