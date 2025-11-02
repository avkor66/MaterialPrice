package org.calculator.materialprice.util;

import org.calculator.materialprice.domain.MetalProduct;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetalParser {
    private static final Pattern SIZE_PATTERN = Pattern.compile(  "(ду\\d+[.,]?\\d*([х*]\\d+[.,]?\\d*)?)" +                                       // ду25х2,8
                                                                        "|(\\d+(?:[.,]?\\d*)?(?:[х*]\\d+(?:[.,]?\\d*)?(?:/\\d+(?:[.,]?\\d*)?)?)+)" +    // 325х24/26, 100х100х4
                                                                        "|(\\d+[A-ЯA-Z])" +                                                             // 30П, 20Б, 75У
                                                                        "|(\\d+мм)" +                                                                   // 2мм, 3мм
                                                                        "|(ф\\d+[.,]?\\d*мм)" +                                                         // ф3мм, ф4 мм
                                                                        "|(?<![A-Za-zА-Яа-я])\\d{1,3}(?![A-Za-zА-Яа-я0-9])");                           // одиночные числа: 27, 8, 250
    private static final Pattern STEEL_PATTERN = Pattern.compile("ст[0-9А-ЯХСНДГспк\\-]*|нерж\\.?|А500С|А240С", Pattern.CASE_INSENSITIVE);

    public static MetalProduct parseLine(String line) {
        MetalProduct product = new MetalProduct();

        if (line == null || line.isBlank()) {
            product.name = "";
            product.size = "";
            product.steelGrade = "";
            product.comment = "";
            return product;
        }

        line = line.trim();

        Matcher mSize = SIZE_PATTERN.matcher(line);
        String size = "";
        int sizeStart = -1;
        int sizeEnd = -1;
        if (mSize.find()) {
            size = mSize.group();
            sizeStart = mSize.start();
            sizeEnd = mSize.end();
        }
        product.size = size;

        product.name = sizeStart > 0 ? line.substring(0, sizeStart).trim() : line;

        String afterSize = sizeEnd > 0 ? line.substring(sizeEnd).trim() : "";
        Matcher mSteel = STEEL_PATTERN.matcher(afterSize);
        String steel = "";
        int steelEnd = -1;
        if (mSteel.find()) {
            steel = mSteel.group();
            steelEnd = mSteel.end();
        }
        product.steelGrade = steel;

        if (steelEnd > 0) {
            product.comment = afterSize.substring(steelEnd).trim();
        } else {
            product.comment = afterSize;
        }

        return product;
    }
}
