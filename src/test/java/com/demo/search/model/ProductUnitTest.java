package com.demo.search.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductUnitTest {

	Product product;
	Size sizeSpecial, sizeBackSoon, sizeNormal;
	Stock stock, stockZero;

	@BeforeEach
	void beforeEach(){
		stock = Stock.builder().id(1).quantity(5).build();
		stockZero = Stock.builder().id(2).quantity(0).build();
		sizeNormal = Size.builder().id(1).backSoon(false).special(false).build();
		sizeBackSoon = Size.builder().id(3).backSoon(true).special(false).build();
		sizeSpecial = Size.builder().id(2).backSoon(true).special(true).build();
		product = Product.builder().id(1).sequence(2).build();
	}

	@Test
	void productIsVisible() {
		sizeNormal.setStocks(List.of(stock));
		product.setSizes(List.of(sizeNormal));
		assertEquals(Boolean.TRUE, product.isVisible());
	}

	@Test
	void productIsVisible2() {
		sizeNormal.setStocks(List.of(stockZero));
		product.setSizes(List.of(sizeNormal));
		assertEquals(Boolean.FALSE, product.isVisible());
	}

	@Test
	void productIsVisible3() {
		sizeNormal.setStocks(List.of(stock));
		product.setSizes(List.of(sizeBackSoon));
		assertEquals(Boolean.TRUE, product.isVisible());
	}

	@Test
	void productIsVisible4() {
		sizeNormal.setStocks(List.of(stockZero));
		product.setSizes(List.of(sizeBackSoon));
		assertEquals(Boolean.TRUE, product.isVisible());
	}

	@Test
	void productIsVisible5() {
		sizeNormal.setStocks(List.of(stock));
		product.setSizes(List.of(sizeSpecial));
		assertEquals(Boolean.FALSE, product.isVisible());
	}

	@Test
	void productIsVisible6() {
		sizeNormal.setStocks(List.of(stockZero));
		product.setSizes(List.of(sizeSpecial));
		assertEquals(Boolean.FALSE, product.isVisible());
	}
}
