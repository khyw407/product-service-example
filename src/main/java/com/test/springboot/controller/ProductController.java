package com.test.springboot.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.domain.Product;
import com.test.springboot.repository.ProductRepository;

@RestController
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	ProductRepository productRepository;
	
	@PostMapping
	public Product add(@RequestBody Product product) {
		return productRepository.add(product);
	}
	
	@PutMapping
	public Product update(@RequestBody Product product) {
		return productRepository.update(product);
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") Long id) {
		return productRepository.findById(id);
	}
	
	@PostMapping("/ids")
	public List<Product> find(@RequestBody List<Long> ids) throws Exception{
		List<Product> products = productRepository.find(ids);
		LOGGER.info("Products found: {}", mapper.writeValueAsString(Collections.singletonMap("count", products.size())));
		
		return products;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		productRepository.delete(id);
	}
}
