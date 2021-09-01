package com.example.recipes_frontend.modelUI;

import java.util.Set;

public class Product {
	
	private long id;
	
	private String name;
	
	private Set<ProductUsage> prodUsages;
	
	public Product () {}
	
	public Product (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}

	
	
}
