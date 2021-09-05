package com.example.recipes_frontend.modelUI;


import java.util.List;
import java.util.Set;




public class RecipeStep {
	
	
	private Recipe id;
	
	private long tempId;
	
	private long eqUsId;
	
	private List<Long> prodUsIds;
	
	private String name;
	
	private String temporaryEquipmentName;
	
	List<String> tempProductNames;
	
	public RecipeStep () {}
		
	
	public RecipeStep(Recipe recipe, String name, EquipmentUsage equipmentUsage, List<ProductUsage> usedProducts) {
		this.id = recipe;
		this.name = name;
		this.equipmentUsage = equipmentUsage;
		this.usedProducts = usedProducts;
	}
		
		
	public RecipeStep(RecipeStepId recipeStepId, long tempId, long eqUsId, List<Long> prodUsIds, String name) {
		this.tempId = tempId;
		this.name = name;
		this.eqUsId = eqUsId;
		this.prodUsIds = prodUsIds;
	}
	
	public RecipeStep(long tempId, long eqUsId, String name) {
		this.tempId = tempId;
		this.name = name;
		this.eqUsId = eqUsId;
	}
	
	public RecipeStep(String tempEqName, String name, List<String> tempProdNames) {
		this.temporaryEquipmentName = tempEqName;
		this.name = name;
		this.tempProductNames = tempProdNames;
	}
	
	public List<String> getTempProductNames() {
		return tempProductNames;
	}


	public void setTempProductNames(List<String> tempProductNames) {
		this.tempProductNames = tempProductNames;
	}

	private EquipmentUsage equipmentUsage;
	
	private List<ProductUsage> usedProducts;
	
	public List<ProductUsage> getUsedProducts() {
		return usedProducts;
	}


	public void setUsedProducts(List<ProductUsage> usedProducts) {
		this.usedProducts = usedProducts;
	}

	RecipeStepId recipeStepId;
	
	
	
	public String getTemporaryEquipmentName() {
		return temporaryEquipmentName;
	}


	public void setTemporaryEquipmentName(String temporaryEquipmentName) {
		this.temporaryEquipmentName = temporaryEquipmentName;
	}

	
	public void setTempId(long tempId) {
		this.tempId = tempId;
	}


	public RecipeStep(long eqUsId, String name) {
		this.name = name;
		this.eqUsId = eqUsId;
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
