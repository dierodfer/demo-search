package com.demo.search.controller;

import com.demo.search.model.Product;
import com.demo.search.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
@Slf4j
class SearchControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SearchService searchService;

	@Test
	void testLoadEndpoint() throws Exception {
		when(searchService.load()).thenReturn(true);
		mockMvc.perform(get("/load"))
				.andExpect(status().isOk())
				.andExpect(content().string(Boolean.TRUE.toString()));
	}

	@Test
	void testListProductsEndpoint() throws Exception {
		Product product1 = Product.builder().id(5).sequence(2).build();
		Product product2 = Product.builder().id(10).sequence(1).build();
		List<Product> productList = List.of(product1, product2);
		when(searchService.getVisibleProducts()).thenReturn(productList);

		MvcResult result = mockMvc.perform(get("/product/list"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		String productListResponse = result.getResponse().getContentAsString();
		assertEquals("[5,10]", productListResponse);
	}
}

