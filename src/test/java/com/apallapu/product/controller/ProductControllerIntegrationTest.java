/**
 * 
 */
package com.apallapu.product.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.apallapu.product.controller.ProductController;
import com.apallapu.product.domain.Product;
import com.apallapu.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author ankamma pallaput
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductRepository productRepository;

	private static final String URI = "http://localhost:1111/product";

	@Test
	public void shouldReturnFullName() throws Exception {
		Product product = new Product(1003, "ankamma", "hyderabad", "9832XXX23", "ankamma.java@mail.com");
		given(productRepository.findProductByProductId(10003)).willReturn(product);
		mockMvc.perform(get("/product/10003")).andExpect(content().json(
				"{\"productId\":1003,\"name\":\"ankamma\",\"address\":\"hyderabad\",\"mobile\":\"9832XXX23\",\"email\":\"ankamma.java@mail.com\"}"))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void allTest() throws Exception {
		List<Product> product = buildProductList();

		when(productRepository.findAll()).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON).content(getJson(product))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(12));

	}

	@Test
	public void saveTest() throws Exception {
		Product product = buildProduct();

		when(productRepository.save(Mockito.any())).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(getJson(product))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(12));

	}

	@Test
	public void updateTest() throws Exception {
		Product product = buildProduct();

		when(productRepository.save(Mockito.any())).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).content(getJson(product))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(12));

	}

	@Test
	public void deleteTest() throws Exception {
		Product product = buildProduct();

		Mockito.doNothing().when(productRepository).delete(Mockito.any());

		mockMvc.perform(MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON).content(getJson(product))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	private byte[] getJson(Object departmentRequest) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(departmentRequest);
		return json.getBytes();
	}

	private List<Product> buildProductList() {

		List<Product> list = new ArrayList<>();
		Product product = new Product();
		product.setProductId(12);
		list.add(product);
		return list;
	}

	private Product buildProduct() {
		Product product = new Product();
		product.setProductId(12);
		return product;
	}

}
