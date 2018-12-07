package com.test.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.springboot.domain.Product;
import com.test.springboot.repository.ProductRepository;

@RestController
public class ProductController {

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
		return products;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		productRepository.delete(id);
	}
}
