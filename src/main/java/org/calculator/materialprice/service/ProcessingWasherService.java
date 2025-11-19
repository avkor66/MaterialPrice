package org.calculator.materialprice.service;

import org.calculator.materialprice.domain.CatalogWasherSizes;
import org.calculator.materialprice.domain.CatalogWasherStandards;
import org.calculator.materialprice.domain.ProductPrice;
import org.calculator.materialprice.dto.WashersPriceRequest;
import org.calculator.materialprice.dto.WashersPriceResponse;
import org.calculator.materialprice.repository.WasherStandardRepository;
import org.calculator.materialprice.util.ParameterParser;
import org.calculator.materialprice.util.WeightParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
public class ProcessingWasherService {

    private String blank;
    private BigDecimal weightPerMM;
    private BigDecimal pricePerKg;
    private BigDecimal thousand = new BigDecimal("1000.00");
    private Boolean isSteelGradeAllowed;
    private BigDecimal blankLength;
    private final WasherStandardRepository washerStandardRepository;
    private final ProductService productService;

    public ProcessingWasherService(WasherStandardRepository washerStandardRepository, ProductService productService) {
        this.washerStandardRepository = washerStandardRepository;
        this.productService = productService;
    }
    public WashersPriceResponse getProceedData(WashersPriceRequest request) {

        WashersPriceResponse result = new WashersPriceResponse();

        CatalogWasherStandards rowStandard = washerStandardRepository.findByStandard(request.getStandard());

        Optional<CatalogWasherSizes> foundWasherSize = rowStandard.getWasherSizes()
                .stream()
                .peek(val -> System.out.println("Проверяемый элемент: " + val.getSize() + " = " + request.getDiameter() + " барабанная дробь..... " + val.getSize().equals(request.getDiameter())))
                .filter(val -> val.getSize().equals(request.getDiameter()))
                .findFirst();

        this.isSteelGradeAllowed = rowStandard.getGrades()
                .stream()
                .anyMatch(val -> val.getWashers().equals(request.getSteelGrade()));

        switch (request.getSpecies()) {
            case "шайба": this.blank =  "Круг"; break;
            case "гайка": this.blank = "Шестигранник"; break;
            case "болт": this.blank = "Шестигранник"; break;
            default: break;
        }

        if (foundWasherSize.isPresent()) {
            BigDecimal outerDiameter = foundWasherSize.get().getOuter_diameter();
            BigDecimal thickness = foundWasherSize.get().getThickness();
            // Получаем общую длину заготовки для всего заказа по формуле - {(толщина + рез) * количество}
            this.blankLength = thickness.add(BigDecimal.valueOf(request.getSawCut().doubleValue()))
                    .multiply(BigDecimal.valueOf(request.getQuantity().doubleValue()));

//            List<ProductPrice> productPrice = productService.getProductsByHer(blank, outerDiameter.toString(), request.getSteelGrade(), blankLength.toString() );
            List<ProductPrice> productPrice = productService.getProductsByName(blank);

//            for (ProductPrice product : productPrice) {
//                // Получаем значение поля dimensions для текущего объекта
//                String dimensions = product.getDimensions();
//
//                // **Здесь вы выполняете нужные действия с dimensions:**
//                // Например:
//                System.out.println("Dimensions для продукта " + product.getProductName() + ": " + dimensions);
//
//                // Или, если dimensions — это строка с размерами, которую нужно обработать:
//                // String processedDimensions = processDimensionsString(dimensions);
//            }

            Optional<ProductPrice> productPriceTwo = productService.getProductsByName(blank)
                    .stream()
                    .filter(product -> {
                        boolean isDiameterOk;
                        try {
                            BigDecimal diam = new BigDecimal(product.getDimensions());
                            isDiameterOk = diam.compareTo(outerDiameter) >= 0;
                        } catch (NumberFormatException e) {
                            isDiameterOk = false;
                        }

//                            Optional<BigDecimal> minLengthOpt = ParameterParser.extractMinLength(product.getParameter());
//                            minLengthOpt.filter(bigDecimal -> bigDecimal.compareTo(blankLength) >= 0).isPresent();


                        return isDiameterOk;
                    })
                    .min(Comparator.comparing(product -> {
                        try {
                            // Создаем компаратор на основе BigDecimal (обрабатывая ошибки, чтобы избежать падения)
                            return new BigDecimal(product.getDimensions());
                        } catch (NumberFormatException e) {
                            // В случае ошибки парсинга возвращаем очень большое значение,
                            // чтобы этот продукт гарантированно не стал минимумом
                            return new BigDecimal(Double.MAX_VALUE);
                        }
                    }));





            System.out.println("Length: " + blankLength + " = " + thickness + " + " + request.getSawCut() + " * " + request.getQuantity());
            productPriceTwo.ifPresent(product -> {
                List<WeightParser.ItemWeight> allWeights = WeightParser.parseWeights(product.getWeight());

                allWeights.stream()
                        .forEach(item -> {
                            if ("п/м".equals(item.unit)){
                                this.weightPerMM = item.weightPerMM;
                                System.out.println("pppppppppppp " + item.unit + "++++" + item.weight + "++++" + item.weightPerMM);
                            } else {
                                Optional<BigDecimal> minLengthOpt = ParameterParser.extractMinLength(product.getParameter());
                                BigDecimal minLength = minLengthOpt.orElse(BigDecimal.ZERO);

                                System.out.println(minLength);
                                System.out.println(item.weight);
                                System.out.println();
                                this.weightPerMM = item.weight.divide(minLength.multiply(this.thousand), 6, BigDecimal.ROUND_HALF_UP);

                                System.out.println("tttttttttttt " + item.unit + "++++" + item.weight + "++++" + item.weightPerMM);
                            }
                        });



                System.out.println("Найден минимальный подходящий продукт:");
                System.out.println("Название: " + product.getProductName());
                System.out.println("Размер: " + product.getDimensions());
                System.out.println("Параметр: " + product.getParameter());
                System.out.println("Параметр парсер: " + ParameterParser.extractMinLength(product.getParameter()));
                System.out.println("Вес: " + product.getWeight());
                System.out.println("Вес парсер: " + allWeights);
                System.out.println("Вес мм: " + allWeights);
                System.out.println("Цена: " + product.getPrice());

                this.pricePerKg = new BigDecimal(product.getPrice());
                this.pricePerKg = this.pricePerKg.divide(this.thousand, 2, BigDecimal.ROUND_HALF_UP);
                // Добавьте сюда другие поля, которые хотите вывести
            });
            System.out.println(productPrice.toString());
        }

        result.setSpecies(request.getSpecies());
        result.setSteelGrade(request.getSteelGrade());
        result.setIsSteelGradeAllowed(this.isSteelGradeAllowed);
        result.setPricePerKg(this.pricePerKg);
        result.setBlankWeightKg(this.blankLength.multiply(this.weightPerMM));
        result.setBlankLengthMM(this.blankLength);
        result.setBlankWeightPerMM(this.weightPerMM);
        result.setTotalPrice(result.getBlankWeightKg().multiply(result.getPricePerKg()));

        System.out.println(foundWasherSize.toString());
        System.out.println(isSteelGradeAllowed);


        System.out.println("request");
        System.out.println(request.getDiameter());
        System.out.println(request.getQuantity());
        System.out.println(request.getSpecies());
        System.out.println(request.getStandard());
        System.out.println(request.getSteelGrade());
        System.out.println(request.getSawCut());

        return result;
    }
}




