package org.calculator.materialprice.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.calculator.materialprice.domain.MetalProduct;
import org.calculator.materialprice.domain.ProductPrice;
import org.calculator.materialprice.util.MetalParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
                    yield isoFormat.format(cell.getDateCellValue());
                } else {
                    yield String.valueOf(cell.getNumericCellValue());
                }
            }
            case BLANK -> null;
            default -> "";
        };
    }
    public static List<ProductPrice> getProductsDataFromExcel(InputStream inputStream) throws IOException {
        List<ProductPrice> productPrices = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowIndex = 0;
        int skipRows = 6;
        for (Row row : sheet) {
            if (rowIndex < skipRows) {
                rowIndex++;
                continue;
            }
            Iterator<Cell> cellIterator = row.iterator();
            int cellIndex = 0;
            Cell contentCell = row.getCell(1);
            if (contentCell == null || contentCell.toString().trim().isEmpty()) {
                break;
            }
            ProductPrice productPrice = new ProductPrice();
            while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 switch (cellIndex) {
                    case 1 -> {
                        productPrice.setContent(getCellValueAsString(cell));
                        MetalProduct parser = MetalParser.parseLine(productPrice.getContent());

                        productPrice.setProductName(parser.name);
                        productPrice.setDimensions(parser.size);
                        productPrice.setSteelGrade(parser.steelGrade);
                        productPrice.setComment(parser.comment);
                    }
                    case 2 -> productPrice.setStandard(getCellValueAsString(cell));
                    case 3 -> productPrice.setParameter(getCellValueAsString(cell));
                    case 4 -> productPrice.setWeight(getCellValueAsString(cell));
                    case 5 -> productPrice.setNumberOfPieces(getCellValueAsString(cell));
                    case 6 -> productPrice.setQuantityWeight(getCellValueAsString(cell));
                    case 7 -> productPrice.setUnitOfMeasurement(getCellValueAsString(cell));
                    case 8 -> productPrice.setPrice(getCellValueAsString(cell));
                    case 9 -> productPrice.setNote(getCellValueAsString(cell));
                    case 10 -> productPrice.setLinkPhoto(getCellValueAsString(cell));
                    default -> {
                    }
                }
                cellIndex++;
            }
            productPrices.add(productPrice);
        }
        return productPrices;
    }
}
