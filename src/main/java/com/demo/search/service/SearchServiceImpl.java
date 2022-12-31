package com.demo.search.service;

import com.demo.search.model.Product;
import com.demo.search.model.Size;
import com.demo.search.model.Stock;
import com.demo.search.repository.ProductRepository;
import com.demo.search.repository.SizeRepository;
import com.demo.search.repository.StockRepository;
import com.demo.search.util.UtilCsv;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired ProductRepository productRepository;
    @Autowired SizeRepository sizeRepository;
    @Autowired StockRepository stockRepository;

    @Override
    @Transactional
    public void load() throws IOException, URISyntaxException, CsvException {
        List<Product> products = UtilCsv.readCsv("product.csv")
                .stream().map(attributes -> Product.builder()
                        .id(Integer.parseInt(attributes[0]))
                        .sequence(Integer.parseInt(attributes[1]))
                        .build()).collect(Collectors.toList());

        List<Size> sizes = UtilCsv.readCsv("size.csv")
                .stream().map(attributes -> Size.builder()
                        .id(Integer.parseInt(attributes[0]))
                        .product(Product.builder().id(Integer.parseInt(attributes[1])).build())
                        .backSoon(Boolean.parseBoolean(attributes[2]))
                        .special(Boolean.parseBoolean(attributes[3]))
                        .build()).collect(Collectors.toList());

        List<Stock> stocks = UtilCsv.readCsv("stock.csv")
                .stream().map(attributes -> Stock.builder()
                        .size(Size.builder().id(Integer.parseInt(attributes[0])).build())
                        .quantity(Integer.parseInt(attributes[1]))
                        .build()).collect(Collectors.toList());

        productRepository.saveAll(products);
        sizeRepository.saveAllAndFlush(sizes);
        stockRepository.saveAll(stocks);
    }

    @Override
    public List<Product> getVisibleProducts() {
        return productRepository.findAll().stream()
                .filter(Product::isVisible)
                .sorted(Comparator.comparing(Product::getSequence))
                .collect(Collectors.toList());
    }
}
