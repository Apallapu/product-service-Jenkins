package com.apallapu.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apallapu.product.domain.Product;
import com.apallapu.product.repository.ProductRepository;

/**
 * @author ankamma pallapu
 *
 */
@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@PostMapping(value = "/product")
	public Product save (@RequestBody Product account){
		return productRepository.save(account);
	}
	
	@GetMapping(value = "/product")
	public Iterable<Product> all (){
		return productRepository.findAll();
	}
	
	@GetMapping(value = "/product/{productId}")
	public Product findByProductId (@PathVariable Integer productId){
		return productRepository.findProductByProductId(productId);
	}
	
	@PutMapping(value = "/product")
	public Product update (@RequestBody Product account){
		return productRepository.save(account);
	}
	
	@DeleteMapping(value = "/product")
	public void delete (@RequestBody Product account){
		productRepository.delete(account);
	}
}
