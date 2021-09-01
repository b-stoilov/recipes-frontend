package com.example.recipes_frontend;

import java.util.List;

import com.example.recipes_frontend.client_service.RecipeService;
import com.example.recipes_frontend.client_service.RecipeServices;
import com.example.recipes_frontend.modelUI.EquipmentUsage;
import com.example.recipes_frontend.modelUI.ProductUsage;
import com.example.recipes_frontend.modelUI.Recipe;
import com.example.recipes_frontend.modelUI.RecipeStep;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


@DesignRoot
    class AllRecipesView extends VerticalLayout {
		Panel panel;
		Panel recipePanel = new Panel("Recipe you selected:");
		static Grid<Recipe> grid;
		static TextField searchField;
		static RecipeService recipesDB = RecipeService.getInstance();
		RecipeView recipeView = new RecipeView();
		HorizontalLayout hl = new HorizontalLayout();
		
		static RecipeServices rs;
		
		public AllRecipesView (Panel panel) {
			this.panel = panel;
			panel.setCaption("Recipes");
			rs = new RecipeServices();
			
			addUI();
			
			addFunctionalityUI();
			
			
			
		}

		private void addFunctionalityUI() {
			grid.asSingleSelect().addSingleSelectionListener(event -> {
				recipeView.getVl_wrapper_steps().removeAllComponents();
				recipeView.getVl_equipment().removeAllComponents();
				recipeView.getVl_products().removeAllComponents();
				
				Recipe recipe = event.getValue();
				
				List<RecipeStep> recipeSteps = null;
				List<EquipmentUsage> eqUsages = null;
				List<ProductUsage> prodUsages = null;
				
				try {
					recipeSteps = rs.getRecipeStepsByRecipeId(recipe.getId());
					eqUsages = rs.getEqUsagesPerRecipe(recipe.getId());
					prodUsages = rs.getProdusagesPerRecipe(recipe.getId());
					
				} catch (Exception e) {
				
				}
				
	            if (event.getValue() == null) {
	                recipeView.setVisible(false);
	            } else {
	                recipeView.setRecipe(event.getValue());
	                
	                
	                if (recipeSteps != null) {
	                	for (RecipeStep recipeStep : recipeSteps) {
		                	recipeView.getVl_wrapper_steps().addComponent(addRecipeStep(recipeStep));
		                }
	                }
	                
	                if (eqUsages != null) {
	                	for (EquipmentUsage eqUsage : eqUsages) {
	                		recipeView.getVl_equipment().addComponent(addEquipmentPerRecipe(eqUsage));
	                	}
	                }
	                
	                if (prodUsages != null) {
	                	for (ProductUsage prodUsage : prodUsages) {
	                		recipeView.getVl_products().addComponent(addProductPerRecipe(prodUsage));
	                	}
	                }
	                
	                	
	                
	                
	                recipePanel.setVisible(true);
	                recipePanel.setContent(recipeView);	  
	                
	            }
	        });
			
			recipeView.btnClose.addClickListener(click -> {
				recipePanel.setVisible(false);
			});
		}

		public VerticalLayout addRecipeStep(RecipeStep recipeStep) {
			
			
				VerticalLayout vl_recipe_step = new VerticalLayout();
            	
            	HorizontalLayout hl_eq_prod_per_step = new HorizontalLayout();
            	
            	VerticalLayout vl_prod_per_step = new VerticalLayout();
            	VerticalLayout vl_eq_per_step = new VerticalLayout(); 
            	
            	Label lblRecipeStepTitle = new Label(
            			String.valueOf(recipeStep.getRecipeStepId().getSequence())
            			+ " " + recipeStep.getName());
            	
            
            	if (recipeStep.getEquipmentUsage() != null) {
            		String usedEquipment = recipeStep.getEquipmentUsage().getEquipment().getEquipmntName();
                	
            		Label lblEquipmentPerStepTitle = new Label("Needed equipment: ");
            		Label lblEquipmentPerStep = new Label(usedEquipment);
            		
            		vl_eq_per_step.addComponents(lblEquipmentPerStepTitle, lblEquipmentPerStep);
            	}
            	
            	
            	
            	hl_eq_prod_per_step.addComponents(vl_prod_per_step, vl_eq_per_step);
            	
            	vl_recipe_step.addComponents(lblRecipeStepTitle, hl_eq_prod_per_step);
		
            	
			
			return vl_recipe_step;
		}
		
		public Label addEquipmentPerRecipe (EquipmentUsage eqUsage) {
			return new Label(eqUsage.getEquipment().getEquipmntName());
		}
		
		public Label addProductPerRecipe (ProductUsage prodUsage) {
			return new Label(prodUsage.getProduct().getName() + " "
								+ prodUsage.getProductQuantity() + " "
								+ prodUsage.getProductUOM().getUomName());
		}

		private void addUI() {
			recipePanel.setVisible(false);
			recipePanel.setWidth("1100");
			
			searchField = new TextField();
			searchField.setPlaceholder("Search for a recipe..");
			searchField.addValueChangeListener(e -> updateList());
	        searchField.setValueChangeMode(ValueChangeMode.LAZY);
	        
			grid = new Grid<>(Recipe.class);
			
			grid.setColumns("id", "name", "desc");
			updateList();
			recipeView.setVisible(false);
			
			hl.addComponents(grid, recipePanel);
			
			this.addComponents(searchField, hl);
		}
		
		public static void updateList() {
			List<Recipe> recipes = rs.getAllRecipes();
			
			grid.setItems(recipes);
		}
	}
