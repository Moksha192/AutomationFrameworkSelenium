package com.training.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvUtil {

    public static Map<String, List<String>> readCSV(String filePath) {

        Map<String, List<String>> map = new LinkedHashMap<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(filePath))) {

            // Read header
            String header = br.readLine();

            if (header == null || header.isBlank()) {
                throw new RuntimeException("CSV file is empty: " + filePath);
            }

            String[] columns = header.split(",", -1);

            // Create map entries for each column
            for (String column : columns) {

                column = column.trim();

                if (column.isEmpty()) {
                    throw new RuntimeException(
                            "CSV contains an empty column name");
                }

                map.put(column, new ArrayList<>());
            }

            // Read data rows
            String line;

            while ((line = br.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                // -1 preserves empty values at the end
                String[] values = line.split(",", -1);

                // Validate number of columns
                if (values.length != columns.length) {

                    throw new RuntimeException(
                            "Invalid CSV row: " + line
                                    + ". Expected "
                                    + columns.length
                                    + " columns but found "
                                    + values.length
                    );
                }

                for (int i = 0; i < columns.length; i++) {

                    map.get(columns[i].trim())
                            .add(values[i].trim());
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read CSV file: " + filePath, e);
        }

        return map;
    }
}