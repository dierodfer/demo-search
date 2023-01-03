package com.demo.search.controller;

import com.demo.search.model.Product;
import com.demo.search.service.SearchService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchControllerImpl implements SearchController{

    @Autowired
    private SearchService searchService;

    @Override
    public Boolean load() throws IOException, URISyntaxException, CsvException {
        return searchService.load();
    }

    @Override
    public List<Integer> listProducts() {
        return searchService.getVisibleProducts().stream().map(Product::getId).collect(Collectors.toList());
    }
}
