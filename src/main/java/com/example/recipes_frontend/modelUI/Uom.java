package com.example.recipes_frontend.modelUI;

import java.util.Set;
                                              

public class Uom {
	
	private long id;
	
	private String name;
	
	private Set<ProductUsage> prodUsages;
	
	public Uom () {}
	
	public Uom(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getUomName() {
		return name;
	}

	public void setUomName(String uomName) {
		this.name = uomName;
	}
	
	
	
	
	
	
	
}
