package com.demo.search.controller;

import com.opencsv.exceptions.CsvException;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface SearchController {
    @GetMapping("/load")
    Boolean load() throws IOException, URISyntaxException, CsvException;
    @GetMapping("/product/list")
    List<Integer> listProducts();
}
