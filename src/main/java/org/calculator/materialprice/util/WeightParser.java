package org.calculator.materialprice.util;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeightParser {

    private static final Pattern WEIGHT_PATTERN = Pattern.compile(
            "(\\d+)(шт|п/м)-(\\d+([,\\.]\\d+)?)кг"
    );

    public static List<ItemWeight> parseWeights(String complexString) {
        List<ItemWeight> results = new ArrayList<>();

        // 1. Очистка и нормализация (заменяем запятые на точки)
        String normalizedString = complexString.replace(',', '.');
        Matcher matcher = WEIGHT_PATTERN.matcher(normalizedString);

        while (matcher.find()) {
            try {
                // Группа 1: Количество (шт/п/м), обычно 1
                // Группа 2: Тип измерения ("шт" или "п/м")
                // Группа 3: Значение веса (например, "1.5", "15")

                String unitType = matcher.group(2);
                BigDecimal weight = new BigDecimal(matcher.group(3));

                // Рассчитываем вес на 1 мм
                BigDecimal weightPerMM;

                if ("п/м".equals(unitType)) {
                    // 1 п/м = 1000 мм
                    // Вес на 1 мм = Вес / 1000
                    weightPerMM = weight.divide(new BigDecimal("1000.00"), 10, BigDecimal.ROUND_HALF_UP);
                } else {
                    // Для "шт" нельзя вычислить кг/мм без знания длины.
                    // Если вам нужно это значение для сравнения,
                    // можно вернуть 0 или оставить как null
                    weightPerMM = BigDecimal.ZERO;
                }

                results.add(new ItemWeight(unitType, weight, weightPerMM));

            } catch (Exception e) {
                // Игнорируем некорректно спарсенные записи
                System.err.println("Ошибка парсинга записи: " + matcher.group(0));
            }
        }
        return results;
    }

    // Класс-контейнер для хранения результата

    public static class ItemWeight {
        public final String unit;
        public final BigDecimal weight;
        public final BigDecimal weightPerMM; // Вес в кг/мм

        public ItemWeight(String unit, BigDecimal weight, BigDecimal weightPerMM) {
            this.unit = unit;
            this.weight = weight;
            this.weightPerMM = weightPerMM;
        }

        @Override
        public String toString() {
            return String.format("%s: %s кг (%.6f кг/мм)", unit, weight, weightPerMM);
        }
    }
}