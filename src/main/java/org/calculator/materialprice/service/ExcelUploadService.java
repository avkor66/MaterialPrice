package org.calculator.materialprice.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.calculator.materialprice.domain.MetalProduct;
import org.calculator.materialprice.domain.Product;
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
    public static List<Product> getProductsDataFromExcel(InputStream inputStream) throws IOException {
        List<Product> products = new ArrayList<>();

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
            Product product = new Product();
            while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 switch (cellIndex) {
                    case 1 -> {
                        product.setContent(getCellValueAsString(cell));
                        MetalProduct parser = MetalParser.parseLine(product.getContent());

                        product.setProductName(parser.name);
                        product.setDimensions(parser.size);
                        product.setSteelGrade(parser.steelGrade);
                        product.setComment(parser.comment);
                    }
                    case 2 -> product.setStandard(getCellValueAsString(cell));
                    case 3 -> product.setParameter(getCellValueAsString(cell));
                    case 4 -> product.setWeight(getCellValueAsString(cell));
                    case 5 -> product.setNumberOfPieces(getCellValueAsString(cell));
                    case 6 -> product.setQuantityWeight(getCellValueAsString(cell));
                    case 7 -> product.setUnitOfMeasurement(getCellValueAsString(cell));
                    case 8 -> product.setPrice(getCellValueAsString(cell));
                    case 9 -> product.setNote(getCellValueAsString(cell));
                    case 10 -> product.setLinkPhoto(getCellValueAsString(cell));
                    default -> {
                    }
                }
                cellIndex++;
            }
            products.add(product);
        }
        return products;
    }
}
