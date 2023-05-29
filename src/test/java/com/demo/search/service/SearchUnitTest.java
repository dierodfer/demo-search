package com.demo.search.service;

import com.demo.search.model.Product;
import com.demo.search.model.Size;
import com.demo.search.model.Stock;
import com.demo.search.repository.ProductRepository;
import com.demo.search.repository.SizeRepository;
import com.demo.search.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SearchUnitTest {

	@InjectMocks
	SearchServiceImpl searchService;
	@Mock
	ProductRepository productRepository;
	@Mock
	SizeRepository sizeRepository;
	@Mock
	StockRepository stockRepository;

	Product product1, product2;
	Size sizeSpecial, sizeBackSoon, sizeNormal;
	Stock stock, stockZero;

	@BeforeEach
	void beforeEach(){
		stock = Stock.builder().id(1).quantity(5).build();
		stockZero = Stock.builder().id(2).quantity(0).build();
		sizeNormal = Size.builder().id(1).backSoon(false).special(false).build();
		sizeBackSoon = Size.builder().id(3).backSoon(true).special(false).build();
		sizeSpecial = Size.builder().id(2).backSoon(true).special(true).build();
		product1 = Product.builder().id(1).sequence(2).build();
		product2 = Product.builder().id(2).sequence(1).build();
		when(productRepository.findAll()).thenReturn(List.of(product1,product2));
	}

	@Test
	void businessRuleConditionZero() {
		sizeNormal.setStocks(List.of(stock));
		product1.setSizes(List.of(sizeNormal));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void businessRuleFirstCondition() {
		sizeBackSoon.setStocks(List.of(stockZero));
		product1.setSizes(List.of(sizeBackSoon));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void businessRuleSecondConditionA() {
		product1.setSizes(List.of(sizeBackSoon,sizeSpecial));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void businessRuleSecondConditionB() {
		sizeNormal.setStocks(List.of(stock));
		product1.setSizes(List.of(sizeNormal,sizeSpecial));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void businessRuleSecondConditionC() {
		product1.setSizes(List.of(sizeSpecial));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(0,products.size());
	}

}
