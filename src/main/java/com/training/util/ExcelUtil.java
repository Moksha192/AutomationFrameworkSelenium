package com.training.util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private ExcelUtil() {
        // Utility class
    }

    public static Object[][] readExcel(
            String filePath,
            String sheetName) {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName);
            }

            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            Row headerRow = sheet.getRow(firstRow);

            if (headerRow == null) {
                throw new RuntimeException(
                        "Excel sheet is empty: " + sheetName);
            }

            int firstColumn = headerRow.getFirstCellNum();
            int lastColumn = headerRow.getLastCellNum();

            int rowCount = lastRow - firstRow;
            int columnCount = lastColumn - firstColumn;

            Object[][] data =
                    new Object[rowCount][columnCount];

            DataFormatter formatter = new DataFormatter();

            for (int i = 0; i < rowCount; i++) {

                Row row = sheet.getRow(firstRow + i + 1);

                for (int j = 0; j < columnCount; j++) {

                    Cell cell = row == null
                            ? null
                            : row.getCell(
                            firstColumn + j,
                            Row.MissingCellPolicy
                                    .CREATE_NULL_AS_BLANK
                    );

                    data[i][j] =
                            formatter.formatCellValue(cell).trim();
                }
            }

            return data;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file: " + filePath,
                    e
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to process Excel file: " + filePath,
                    e
            );
        }
    }
}