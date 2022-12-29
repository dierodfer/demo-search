package com.demo.search.service;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface SearchService {

    void load() throws IOException, URISyntaxException, CsvException;
}
