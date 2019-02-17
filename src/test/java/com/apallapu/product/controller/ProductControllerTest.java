/**
 * 
 */
package com.apallapu.product.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.apallapu.product.controller.ProductController;
import com.apallapu.product.domain.Product;
import com.apallapu.product.repository.ProductRepository;

/**
 * @author ankamma pallapu
 *
 */
public class ProductControllerTest {
	
	ProductController productController;
	
	@Mock
	ProductRepository productRepository;
	
	@Before
	public void setUp() throws Exception {
		initMocks(this);
		productController = new ProductController(productRepository);
	}
	
	@Test
	public void findByAccountId (){
		Product account = new Product(1003, "ankamma", "hyderabad", "9832XXX23", "ankamma.java@mail.com");
		given(productRepository.findProductByProductId(10003)).willReturn(account);
		Product acct = productController.findByProductId(10003);
        assertThat(acct.getName(), is("ankamma"));
	}
}
