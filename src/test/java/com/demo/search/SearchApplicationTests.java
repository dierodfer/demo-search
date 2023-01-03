package com.demo.search;

import com.demo.search.model.Product;
import com.demo.search.model.Size;
import com.demo.search.model.Stock;
import com.demo.search.repository.ProductRepository;
import com.demo.search.repository.SizeRepository;
import com.demo.search.repository.StockRepository;
import com.demo.search.service.SearchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SearchApplicationTests {

	@InjectMocks
	SearchServiceImpl searchService;
	@Mock
	ProductRepository productRepository;
	@Mock
	SizeRepository sizeRepository;
	@Mock
	StockRepository stockRepository;

	Product product1;
	Product product2;
	Size sizeSpecial;
	Size sizeBackSoon;
	Size sizeNormal;
	Stock stock;
	Stock stockZero;

	@BeforeEach
	void beforeEach(){
		stock = Stock.builder().id(1).quantity(5).build();
		stockZero = Stock.builder().id(2).quantity(0).build();
		sizeNormal = Size.builder().id(1).backSoon(false).special(false).build();
		sizeBackSoon = Size.builder().id(3).backSoon(true).special(false).build();
		sizeSpecial = Size.builder().id(2).backSoon(true).special(true).build();
		product1 = Product.builder().id(1).sequence(2).build();
		product2 = Product.builder().id(2).sequence(1).build();
	}

	@Test
	void bussinessRuleConditionZero() {
		sizeNormal.setStocks(List.of(stock));
		product1.setSizes(List.of(sizeNormal));
		when(productRepository.findAll()).thenReturn(List.of(product1,product2));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void bussinessRuleFirstCondition() {
		sizeBackSoon.setStocks(List.of(stockZero));
		product1.setSizes(List.of(sizeBackSoon));
		when(productRepository.findAll()).thenReturn(List.of(product1,product2));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void bussinessRuleSecondConditionA() {
		product1.setSizes(List.of(sizeBackSoon,sizeSpecial));
		when(productRepository.findAll()).thenReturn(List.of(product1,product2));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void bussinessRuleSecondConditionB() {
		sizeNormal.setStocks(List.of(stock));
		product1.setSizes(List.of(sizeNormal,sizeSpecial));
		when(productRepository.findAll()).thenReturn(List.of(product1,product2));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(1,products.size());
		assertEquals(1,products.get(0).getId());
	}

	@Test
	void bussinessRuleSecondConditionC() {
		product1.setSizes(List.of(sizeSpecial));
		when(productRepository.findAll()).thenReturn(List.of(product1,product2));
		List<Product> products = searchService.getVisibleProducts();
		assertEquals(0,products.size());
	}

}
