package com.example.recipes_frontend.modelUI;

import java.util.List;
import java.util.Set;

public class EquipmentUsage {
	
	private long id;
	
	private Equipment equipment;
	
	private Recipe recipe;
	
	private Set<RecipeStep> recipeStep;
	
	private long recipeIdd;

	
	public EquipmentUsage() {
	}


	public EquipmentUsage(Equipment equipment, Recipe recipe, Set<RecipeStep> recipeStep) {
		
		this.equipment = equipment;
		this.recipe = recipe;
		this.recipeStep = recipeStep;
	}
	
	
	public EquipmentUsage(Equipment equipment, long recipeIdd) {
		this.equipment = equipment;
		this.recipeIdd = recipeIdd;
	}
	
	public EquipmentUsage(Equipment equipment) {
		this.equipment = equipment;
	}


	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	
	public long getRecipeIdd() {
		return recipeIdd;
	}

	public void setRecipeIdd(long recipeIdd) {
		this.recipeIdd = recipeIdd;
	}


	public Equipment getEquipment() {
		return equipment;
	}


	public void setEquipment(Equipment object) {
		this.equipment =  object;
	}


	public Recipe getRecipe() {
		return recipe;
	}


	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}


	

	

}
