package com.example.recipes_frontend.modelUI;

import java.util.Set;

public class ProductUsage {

	private long id;
	
	private Product product;
	
	private double productQuantity;
	
	private Uom productUOM;
	
	private Recipe recipe;
	
	private Set<RecipeStep> recipeSteps;
		
	private long recipeIdd;
	
	public ProductUsage () {}
	
	public ProductUsage(Product product, double productQuantity, Uom productUOM, Recipe recipe) {
		this.product = product;
		this.productQuantity = productQuantity;
		this.productUOM = productUOM;
		this.recipe = recipe;
	}
	
	public ProductUsage(Product product, double productQuantity, Uom productUOM, long recipeIdd) {
		this.product = product;
		this.productQuantity = productQuantity;
		this.productUOM = productUOM;
		this.recipeIdd = recipeIdd;
	}
	
	public ProductUsage(Product product, double productQuantity, Uom productUOM) {
		this.product = product;
		this.productQuantity = productQuantity;
		this.productUOM = productUOM;
	}

	public long getId() {
		return id;
	}
	
	public void setProduct (Product product) {
		this.product = product;
	}
	
	public Product getProduct () {
		return product;
	}

	public double getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(double productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Uom getProductUOM() {
		return productUOM;
	}

	public void setProductUOM(Uom productUOM) {
		this.productUOM = productUOM;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public long getRecipeIdd() {
		return recipeIdd;
	}

	public void setRecipeIdd(long recipeIdd) {
		this.recipeIdd = recipeIdd;
	}
	
	
	
	
}
