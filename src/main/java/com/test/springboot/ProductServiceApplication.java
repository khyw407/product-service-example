package com.test.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.domain.Order;
import com.test.springboot.domain.Product;
import com.test.springboot.repository.ProductRepository;
import com.test.springboot.service.ProductService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Processor.class)
public class ProductServiceApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceApplication.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	ProductService productService;

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	
	@StreamListener(Processor.INPUT)
	public void receiveOrder(Order order) throws JsonProcessingException {
		LOGGER.info("Order received: {}", objectMapper.writeValueAsString(order));
		productService.process(order);
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
		productRepository.add(new Product("Test1", 1000, 20));
		productRepository.add(new Product("Test2", 1500, 10));
		productRepository.add(new Product("Test3", 2000, 20));
		productRepository.add(new Product("Test4", 3000, 20));
		productRepository.add(new Product("Test5", 1300, 10));
		productRepository.add(new Product("Test6", 2700, 10));
		productRepository.add(new Product("Test7", 3500, 10));
		productRepository.add(new Product("Test8", 1250, 10));
		productRepository.add(new Product("Test9", 2450, 10));
		productRepository.add(new Product("Test10", 800, 10));
		
		return productRepository;
	}
}
