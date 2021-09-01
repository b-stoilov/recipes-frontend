package com.example.recipes_frontend.modelUI;


import java.util.List;
import java.util.Set;




public class RecipeStep {
	
	
	private Recipe id;
	
	private long tempId;
	
	private long eqUsId;
	
	private List<Long> prodUsIds;
	
	private String name;
	
	private EquipmentUsage equipmentUsage;
	
	private Set<ProductUsage> usedProducts;
	
	RecipeStepId recipeStepId;
	
	public RecipeStep () {}
		
	
	public RecipeStep(Recipe recipe, String name, EquipmentUsage equipmentUsage) {
		this.id = recipe;
		this.name = name;
		this.equipmentUsage = equipmentUsage;
//			this.productUsages = productUsages;
	}
		
		
	public RecipeStep(RecipeStepId recipeStepId, long tempId, long eqUsId, List<Long> prodUsIds, String name) {
		this.tempId = tempId;
		this.name = name;
		this.eqUsId = eqUsId;
		this.prodUsIds = prodUsIds;
	}
	
	public RecipeStepId getRecipeStepId() {
		return recipeStepId;
	}

	public void setRecipeStepId(RecipeStepId recipeStepId) {
		this.recipeStepId = recipeStepId;
	}

	
	public long getEqUsId() {
		return eqUsId;
	}

	public void setEqUsId(long eqUsId) {
		this.eqUsId = eqUsId;
	}

	public List<Long> getProdUsIds() {
		return prodUsIds;
	}

	public void setProdUsIds(List<Long> prodUsIds) {
		this.prodUsIds = prodUsIds;
	}

	public EquipmentUsage getEquipmentUsage() {
		return equipmentUsage;
	}

	public void setEquipmentUsage(EquipmentUsage equipmentUsage) {
		this.equipmentUsage = equipmentUsage;
	}

	public Recipe getRecipe() {
		return id;
	}
	
	public long getTempId () {
		return tempId;
	}

	public void setRecipe(Recipe recipe) {
		this.id = recipe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
