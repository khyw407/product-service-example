package com.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import domain.Product;
import repository.ProductRepository;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	
	@Bean
	ProductRepository productRepository() {
		ProductRepository productRepository = new ProductRepository();
		productRepository.add(new Product("Test1", 1000));
		productRepository.add(new Product("Test2", 1500));
		productRepository.add(new Product("Test3", 2000));
		productRepository.add(new Product("Test4", 3000));
		productRepository.add(new Product("Test5", 1300));
		productRepository.add(new Product("Test6", 2700));
		productRepository.add(new Product("Test7", 3500));
		productRepository.add(new Product("Test8", 1250));
		productRepository.add(new Product("Test9", 2450));
		productRepository.add(new Product("Test10", 800));
		
		return productRepository;
	}
}
