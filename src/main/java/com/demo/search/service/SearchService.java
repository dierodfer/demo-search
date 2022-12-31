package com.demo.search.service;

import com.demo.search.model.Product;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface SearchService {

    void load() throws IOException, URISyntaxException, CsvException;
    List<Product> getVisibleProducts();
}
