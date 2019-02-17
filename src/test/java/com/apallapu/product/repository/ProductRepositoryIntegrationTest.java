/**
 * 
 */
package com.apallapu.product.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.apallapu.product.domain.Product;
import com.apallapu.product.repository.ProductRepository;

/**
 * @author ankamma pallapu
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {

	@Autowired
	private ProductRepository productRepository;

	@After
	public void tearDown() throws Exception {
		productRepository.deleteAll();
	}

	@Test
	@Sql(statements = {
			"INSERT INTO PRODUCT (productId, NAME, ADDRESS, EMAIL, MOBILE) values (1002, 'ankamma', 'Hyderabad', 'ankammaxxx@gmail.com', '99352XXX12')",
			"INSERT INTO PRODUCT (productId, NAME, ADDRESS, EMAIL, MOBILE) values (1001, 'ankamma', 'Hyderabad', 'ankammaxxx@gmail.com', '68312XXX13')" })
	public void shouldSaveAndFetchProduct() throws Exception {
		Product product = productRepository.findProductByProductId(1002);
		Assertions.assertThat(product).isNotNull();
		Assertions.assertThat(product.getProductId()).isEqualTo(1002);
		Assertions.assertThat(product.getAddress()).isEqualTo("Hyderabad");
		Assertions.assertThat(product.getEmail()).isEqualTo("ankammaxxx@gmail.com");
		Assertions.assertThat(product.getMobile()).isEqualTo("99352XXX12");

	}

	@Test
	@Sql(statements = {
			"INSERT INTO PRODUCT (productId, NAME, ADDRESS, EMAIL, MOBILE) values (1002, 'ankamma', 'Hyderabad', 'ankammaxxx@gmail.com', '99352XXX12')",
			"INSERT INTO PRODUCT (productId, NAME, ADDRESS, EMAIL, MOBILE) values (1001, 'ankamma', 'Hyderabad', 'ankammaxxx@gmail.com', '68312XXX13')" })
	public void findAllListTest() throws Exception {
		List<Product> productList = (List<Product>) productRepository.findAll();
		Assertions.assertThat(productList).isNotNull();
		Assertions.assertThat(productList.get(0).getProductId()).isEqualTo(1001);
		Assertions.assertThat(productList.get(0).getAddress()).isEqualTo("Hyderabad");
		Assertions.assertThat(productList.get(0).getEmail()).isEqualTo("ankammaxxx@gmail.com");
		Assertions.assertThat(productList.get(0).getMobile()).isEqualTo("68312XXX13");

	}

	@Test
	public void saveTest() throws Exception {
		Product productList = productRepository.save(buildProduct());
		Assertions.assertThat(productList).isNotNull();
		Assertions.assertThat(productList.getProductId()).isEqualTo(1);

	}

	@Test
	public void updateTest() throws Exception {
		Product productList = productRepository.save(updateProduct());
		Assertions.assertThat(productList).isNotNull();
		Assertions.assertThat(productList.getProductId()).isEqualTo(1003);

	}

	@Test
	public void deleteTest() throws Exception {
		productRepository.delete(buildProduct());

	}

	private Product buildProduct() {
		Product product = new Product();
		product.setProductId(1);
		return product;
	}
	
	private Product updateProduct() {
		Product product = new Product();
		product.setProductId(1003);
		return product;
	}
}
