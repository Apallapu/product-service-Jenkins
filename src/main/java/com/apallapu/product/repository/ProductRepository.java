/**
 * 
 */
package com.apallapu.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.apallapu.product.domain.Product;

/**
 * @author ankamma pallapu
 *
 */
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	Product findProductByProductId(Integer productId);
}
