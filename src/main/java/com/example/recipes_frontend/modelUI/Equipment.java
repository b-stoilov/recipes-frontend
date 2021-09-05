package com.example.recipes_frontend.modelUI;

import java.util.Set;

public class Equipment {
	
	private long id;
	
	private String name;
	
	private Set<EquipmentUsage> eqUsages;
	
	public Equipment() {
		
	}

	public Equipment(long id, String equipmntName) {
		this.id = id;
		this.name = equipmntName;
	}
	
	public Equipment(String equipmntName) {
	
		this.name = equipmntName;
	}

	public String getEquipmntName() {
		return name;
	}

	public void setEquipmntName(String equipmntName) {
		this.name = equipmntName;
	}
	
	public long getId () {
		return id;
	}

	

	
}
