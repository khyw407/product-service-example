package com.test.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Product {

	private Long id;
	private String name;
	private int price;
	private int count;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public Product(String name, int price, int count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}
}
