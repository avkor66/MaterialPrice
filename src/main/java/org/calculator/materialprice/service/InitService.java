package org.calculator.materialprice.service;

import jakarta.transaction.Transactional;
import org.calculator.materialprice.domain.*;
import org.calculator.materialprice.repository.*;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private final SteelStandardRepository steelStandardRepository;
    private final SteelGradeRepository steelGradeRepository;
    private final WasherStandardRepository washerStandardRepository;
    private final WasherSizeRepository washerSizeRepository;
    private final BoltStandardRepository boltStandardRepository;
    private final BoltSizeRepository boltSizeRepository;
    private final NutStandardRepository nutStandardRepository;
    private final NutSizeRepository nutSizeRepository;

    public InitService(
            SteelStandardRepository standardRepo,
            SteelGradeRepository gradesRepo,
            WasherSizeRepository washerSizeRepository,
            WasherStandardRepository washerStandardRepository,
            BoltStandardRepository boltStandardRepository,
            BoltSizeRepository boltSizeRepository,
            NutStandardRepository nutStandardRepository,
            NutSizeRepository nutSizeRepository
    ) {
        this.steelStandardRepository = standardRepo;
        this.steelGradeRepository = gradesRepo;
        this.washerStandardRepository = washerStandardRepository;
        this.washerSizeRepository = washerSizeRepository;
        this.boltStandardRepository = boltStandardRepository;
        this.boltSizeRepository = boltSizeRepository;
        this.nutStandardRepository = nutStandardRepository;
        this.nutSizeRepository = nutSizeRepository;
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

