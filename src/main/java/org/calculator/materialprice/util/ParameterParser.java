package org.calculator.materialprice.util;

import java.math.BigDecimal;
import java.util.Optional;

public class ParameterParser {
    /**
     * Извлекает минимально возможное числовое значение длины из сложной строки параметра.
     * Возвращает Optional.empty(), если парсинг не удался.
     */
    public static Optional<BigDecimal> extractMinLength(String parameter) {
        if (parameter == null || parameter.isEmpty()) {
            return Optional.empty();
        }

        // 1. Предварительная очистка и стандартизация
        String cleaned = parameter
                .replace(',', '.') // Заменяем запятую на точку
                .replaceAll("\\(.*?\\)", "") // Удаляем (1шт), (2шт)
                .replace("м", "") // Удаляем единицу измерения
                .trim();

        // 2. Определяем, есть ли диапазон (дефис) или сложение (плюс)
        String[] parts;
        if (cleaned.contains("-")) {
            // "3.8-5.85" -> ["3.8", "5.85"]
            parts = cleaned.split("-");
        } else if (cleaned.contains("+")) {
            // "5-5.1+1.445" -> ["5-5.1", "1.445"] (берем только первое число в данном случае)
            parts = cleaned.split("\\+");
        } else {
            // "6" -> ["6"]
            parts = new String[]{cleaned};
        }

        // 3. Пытаемся взять первое числовое значение (которое является минимумом)
        String minPart = parts[0].trim();

        // Если в первой части все еще есть дефис, берем только число до дефиса
        if (minPart.contains("-")) {
            minPart = minPart.split("-")[0].trim();
        }

        // Финальная попытка парсинга
        try {
            if (minPart.matches("-?\\d+(\\.\\d+)?")) { // Проверяем, похоже ли это на число
                return Optional.of(new BigDecimal(minPart));
            }
        } catch (NumberFormatException e) {
            // Ошибка, если строка не является числом
            // Просто возвращаем Optional.empty()
        }

        return Optional.empty();
    }
}
