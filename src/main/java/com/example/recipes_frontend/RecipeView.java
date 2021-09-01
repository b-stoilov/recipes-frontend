package com.example.recipes_frontend;

import java.util.List;

import com.example.recipes_frontend.modelUI.Recipe;
import com.example.recipes_frontend.modelUI.RecipeStep;
import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;



public class RecipeView extends VerticalLayout {
	public Button btnClose;
	private TextField name;
	private TextField desc;
	private Binder<Recipe> binder = new Binder<>(Recipe.class);
	HorizontalLayout hl_all_steps = new HorizontalLayout();
	
	VerticalLayout vl_steps = new VerticalLayout();
	VerticalLayout vl_wrapper_steps = new VerticalLayout();
	
	VerticalLayout vl_equipment = new VerticalLayout();
	VerticalLayout vl_equipment_wrapper = new VerticalLayout();
	
	VerticalLayout vl_products = new VerticalLayout();
	VerticalLayout vl_products_wrapper = new VerticalLayout();
	

	public VerticalLayout getVl_products() {
		return vl_products;
	}

	public VerticalLayout getVl_equipment() {
		return vl_equipment;
	}

	public VerticalLayout getVl_wrapper_steps() {
		return vl_wrapper_steps;
	}

	public void setVl_steps(VerticalLayout vl_wrapper_steps) {
		this.vl_wrapper_steps = vl_wrapper_steps;
	}

	public RecipeView () {
		
		
		
		VerticalLayout vl_all = new VerticalLayout();
		HorizontalLayout hl_name_desc = new HorizontalLayout();
		
		Label lblSteps = new Label("Steps of the recipe:");
		
		vl_steps.addComponents(lblSteps, vl_wrapper_steps);
		
		HorizontalLayout hl_products_equipment = new HorizontalLayout();
		
		
		Label lblProducts = new Label("Needed products:");
		
		vl_products_wrapper.addComponents(lblProducts, vl_products);
		
		
		Label lblEquipment = new Label("Needed equipment");
		
		vl_equipment_wrapper.addComponents(lblEquipment, vl_equipment);

		
		hl_products_equipment.addComponents(vl_products_wrapper, vl_equipment_wrapper);
		
		btnClose = new Button("Close");
		
		name = new TextField("Name:");
		name.setReadOnly(true);
		
		desc = new TextField("Description:");
		desc.setReadOnly(true);
		
		binder.bindInstanceFields(this);
		
		
		hl_name_desc.addComponents(name , desc);
		
		vl_all.addComponents(btnClose, hl_name_desc, hl_products_equipment);
		
		hl_all_steps.addComponents(vl_all, vl_steps);
		this.addComponents(hl_all_steps);
		
	}
	
	public void setRecipe(Recipe recipe) {
		binder.setBean(recipe);
		
		
		
		setVisible(true);
	}
	
	public HorizontalLayout getHl_all_steps() {
		return hl_all_steps;
	}

	public void setHl_all_steps(HorizontalLayout hl_all_steps) {
		this.hl_all_steps = hl_all_steps;
	
	}

}
