package com.demo.search.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SizeUnitTest {

	Size sizeSpecial, sizeBackSoon, sizeNormal;
	Stock stock, stockZero, stockNegative;

	@BeforeEach
	void beforeEach(){
		stock = Stock.builder().id(1).quantity(5).build();
		stockZero = Stock.builder().id(2).quantity(0).build();
		stockNegative = Stock.builder().id(3).quantity(-1).build();
		sizeNormal = Size.builder().id(1).backSoon(false).special(false).build();
		sizeBackSoon = Size.builder().id(3).backSoon(true).special(false).build();
		sizeSpecial = Size.builder().id(2).backSoon(true).special(true).build();
	}

	@Test
	void sizeIsVisible() {
		sizeNormal.setStocks(List.of(stock));
		assertEquals(Boolean.TRUE,sizeNormal.isVisible());
	}

	@Test
	void sizeIsVisible2() {
		sizeNormal.setStocks(List.of(stockZero));
		assertEquals(Boolean.FALSE,sizeNormal.isVisible());
	}

	@Test
	void sizeIsVisible3() {
		sizeNormal.setStocks(List.of(stockNegative));
		assertEquals(Boolean.FALSE,sizeNormal.isVisible());
	}

	@Test
	void sizeIsVisible4() {
		sizeBackSoon.setStocks(List.of(stock));
		assertEquals(Boolean.TRUE,sizeBackSoon.isVisible());
	}

	@Test
	void sizeIsVisible5() {
		sizeBackSoon.setStocks(List.of(stockZero));
		assertEquals(Boolean.TRUE,sizeBackSoon.isVisible());
	}

	@Test
	void sizeIsVisible6() {
		sizeBackSoon.setStocks(List.of(stockNegative));
		assertEquals(Boolean.TRUE,sizeBackSoon.isVisible());
	}

	@Test
	void sizeIsVisible7() {
		sizeSpecial.setStocks(List.of(stock));
		assertEquals(Boolean.TRUE,sizeSpecial.isVisible());
	}

	@Test
	void sizeIsVisible8() {
		sizeSpecial.setStocks(List.of(stockZero));
		assertEquals(Boolean.TRUE,sizeSpecial.isVisible());
	}

	@Test
	void sizeIsVisible9() {
		sizeSpecial.setStocks(List.of(stockNegative));
		assertEquals(Boolean.TRUE,sizeSpecial.isVisible());
	}

}
