package com.example.recipes_frontend.modelUI;

import java.util.Set;

public class Equipment {
	
	private long id;
	
	private String name;
	
	private Set<EquipmentUsage> eqUsages;
	
	public Equipment() {
		
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
