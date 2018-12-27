package com.test.springboot.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.domain.Order;
import com.test.springboot.domain.OrderStatus;
import com.test.springboot.domain.Product;
import com.test.springboot.repository.ProductRepository;

@Service
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderSender orderSender;
	
	public void process(final Order order) throws JsonProcessingException {
		LOGGER.info("Order processed: {}", objectMapper.writeValueAsString(order));
		
		for (Long productId : order.getProductIds()) {
			Product product = productRepository.findById(productId);
			
			if (product.getCount() == 0) {
				order.setStatus(OrderStatus.REJECTED);
				break;
			}
			
			product.setCount(product.getCount() - 1);
			productRepository.update(product);
			
			LOGGER.info("Product updated: {}", objectMapper.writeValueAsString(product));
		}
		
		if (order.getStatus() != OrderStatus.REJECTED) {
			order.setStatus(OrderStatus.ACCEPTED);
		}
		
		LOGGER.info("Order response sent: {}", objectMapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
		
		orderSender.send(order);
	}
}
