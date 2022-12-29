package com.demo.search.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UtilCsv {

    private UtilCsv() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String[]> readCsv(String fileName) throws IOException, CsvException, URISyntaxException {
        Path filePath = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll().stream().map(StringUtils::stripAll).collect(Collectors.toList());
            }
        }
    }
}
