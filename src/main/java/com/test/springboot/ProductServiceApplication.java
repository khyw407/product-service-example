package com.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.test.springboot.domain.Product;
import com.test.springboot.repository.ProductRepository;

@SpringBootApplication
@EnableDiscoveryClient
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
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(true);
	    loggingFilter.setMaxPayloadLength(1000);
	    loggingFilter.setAfterMessagePrefix("REQ:");
	    return loggingFilter;
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
