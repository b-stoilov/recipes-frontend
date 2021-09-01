package com.example.recipes_frontend;

import java.util.ArrayList;
import java.util.List;


import com.example.recipes_frontend.client_service.RecipeService;
import com.example.recipes_frontend.client_service.RecipeServices;
import com.example.recipes_frontend.modelUI.Equipment;
import com.example.recipes_frontend.modelUI.EquipmentUsage;
import com.example.recipes_frontend.modelUI.Product;
import com.example.recipes_frontend.modelUI.ProductUsage;
import com.example.recipes_frontend.modelUI.Recipe;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;




@DesignRoot
class AddRecipeView extends VerticalLayout {
	
	private RecipeService recipesDB = RecipeService.getInstance();
	private Recipe recipe;
	private Binder<Recipe> binder = new Binder<>(Recipe.class);
	
	private HorizontalLayout hl_all = new HorizontalLayout(); 
	private VerticalLayout vl_else = new VerticalLayout();
	private VerticalLayout vl_steps = new VerticalLayout();
	
	private TextField name;
	private TextField desc;
	
	private HorizontalLayout hl_add_options = new HorizontalLayout();
	
	private VerticalLayout vl_add_products = new VerticalLayout();

	private Button btnAddProduct = new Button("Add");
	
	private VerticalLayout vl_add_equipment = new VerticalLayout();
	private Button btnAddEquipment = new Button("Add");
	
	private HorizontalLayout hl_buttons = new HorizontalLayout();
	private Button btnSave;
	private Button btnCancel;
	
	private Button btnAddStep = new Button("Add");
	
	
	List<String> productsUsed = new ArrayList<>();
	ListDataProvider<String> dpProductsUsed = new ListDataProvider<>(productsUsed);
	
	List<String> equipmentUsed = new ArrayList<>();
	ListDataProvider<String> dpEquipmentUsed = new ListDataProvider<>(equipmentUsed);
	
	int stepCounter = 1;
	
	
	RecipeServices recipeServices = new RecipeServices();
	
	public AddRecipeView (Panel panel) {
		initiateUI();

		btnSave.setClickShortcut(KeyCode.ENTER);
		binder.bindInstanceFields(this);
		
		btnSave.addClickListener(e -> save());
		btnCancel.addClickListener(e -> delete());
		
		btnAddProduct.addClickListener(e -> addAnotherProductRow());
		btnAddEquipment.addClickListener(e -> addEuipmentRow());
		
		btnAddStep.addClickListener(e -> addRecipeSteps());
		
		panel.setCaption("Add a recipe");
	}
	
	
	
	private void initiateUI () {
		
		addProductsUI();
		
		addEquipmentUI();
		
		hl_add_options.addComponents(vl_add_products, vl_add_equipment);
		
		addButtonsUI();
		
		vl_else.addComponents(addNameDesc(), hl_add_options, hl_buttons);
		
		addRecipeStepsUI();
			
		hl_all.addComponents(vl_else, vl_steps);
		
		this.addComponent(hl_all);
	}



	private void addRecipeStepsUI() {
	    HorizontalLayout hl_lbl_btn = new HorizontalLayout(new Label("Add steps of recipe: "),
	    		btnAddStep);
		vl_steps.addComponents(hl_lbl_btn, addStep());
	}
		
	
	private void addRecipeSteps () {
		stepCounter ++;
		
		vl_steps.addComponent(addStep());
	}
	
	
	private VerticalLayout addStep () {
		VerticalLayout vl_step = new VerticalLayout();
		
		HorizontalLayout hl_tf_nsp_nse = new HorizontalLayout();
		
		HorizontalLayout hl_prs_eq_per_step = new HorizontalLayout();
		VerticalLayout vl_prs_per_step = new VerticalLayout();
		VerticalLayout vl_eq_per_step = new VerticalLayout();
		
		NativeSelect<String> nsProducts = new NativeSelect<>();
		NativeSelect<String> nsEquipment = new NativeSelect<>();
		
		TextField tfStepName = new TextField();
		Label lblStepCounter = new Label(String.valueOf(stepCounter));
		
		nsProducts.setDataProvider(dpProductsUsed);
		nsEquipment.setDataProvider(dpEquipmentUsed);
		
		
		hl_tf_nsp_nse.addComponents(lblStepCounter, tfStepName,
				nsProducts, nsEquipment);
		
		
		vl_prs_per_step.addComponent(new Label("Products used:"));	
		
		nsProducts.addSelectionListener(valueChange -> {
			if (valueChange.getValue() != null || !valueChange.getValue().isEmpty()) {
				vl_prs_per_step.addComponent(new Label((String) valueChange.getValue()));
			}
			
		});
		
		nsEquipment.addSelectionListener(valueChange -> {
			if (valueChange.getValue() != null || !valueChange.getValue().isEmpty()) {
				vl_eq_per_step.addComponent(new Label((String) valueChange.getValue()));
			}
		});
		
		vl_eq_per_step.addComponent(new Label("Equipment used:"));
		
		hl_prs_eq_per_step.addComponents(vl_prs_per_step, vl_eq_per_step);
		
		vl_step.addComponents(hl_tf_nsp_nse, hl_prs_eq_per_step);
		
		return vl_step;
	}



	private void addButtonsUI() {
		btnSave = new Button("Save");
		
		btnCancel = new Button("Cancel");
		
		hl_buttons.addComponents(btnSave, btnCancel);
	}



	private HorizontalLayout addNameDesc() {
		HorizontalLayout hl_name_desc = new HorizontalLayout();
		
		name = new TextField();
		name.setCaption("Name your recipe:");
	
		desc = new TextField();
		desc.setCaption("Describe your recipe:");
		
		hl_name_desc.addComponents(name, desc);
		
		return hl_name_desc;
	}
	
	
	private void addEquipmentUI() {
		HorizontalLayout hl_lbl_btn = new HorizontalLayout(new Label("Add the needed equipment: "),
				btnAddEquipment);
		vl_add_equipment.addComponents(hl_lbl_btn, addEquipment());
	}
	
	private void addEuipmentRow() {
		vl_add_equipment.addComponent(addEquipment());
	}


	private HorizontalLayout addEquipment() {
		HorizontalLayout hl_equipment_item = new HorizontalLayout();
		TextField tfEqName = new TextField("Name");
		Button btnSubmit = new Button("Yes");
		Button btnDelete = new Button("No");
		
		btnSubmit.addClickListener(click -> {
			if (tfEqName.getValue()  != null || !tfEqName.isEmpty()) {
				equipmentUsed.add(tfEqName.getValue());
				dpEquipmentUsed.refreshAll();
			}
		});
		
		hl_equipment_item.addComponents(tfEqName, btnSubmit, btnDelete);
		
		return hl_equipment_item;
	}


	private void addProductsUI() {
		HorizontalLayout hl_lbl_btn = new HorizontalLayout(new Label("Add the needed products:"), 
				btnAddProduct);
		vl_add_products.addComponents(hl_lbl_btn, addProductRow());
	}
	
	
	public void addAnotherProductRow () {
		vl_add_products.addComponent(addProductRow());
	}
	
	
	public HorizontalLayout addProductRow () {
		HorizontalLayout hl_product_row = new HorizontalLayout();
		TextField tfAddProduct = new TextField("Name:");
		TextField tfAddProductQuantity = new TextField("Quantity:");
		TextField tfAddUOM = new TextField("UOM:");
		Button btnSubmitProduct = new Button("Yes");
		Button btnDeleteProduct = new Button("No");
		
		btnSubmitProduct.addClickListener(click -> {
			if (tfAddProduct.getValue() != null || !tfAddProduct.isEmpty()) {
				productsUsed.add(tfAddProduct.getValue());
				dpProductsUsed.refreshAll();
			}			
		});
		
		btnDeleteProduct.addClickListener(click -> {

		});
		
		tfAddProductQuantity.setWidth("75");
		tfAddUOM.setWidth("75");
		
		hl_product_row.addComponents(tfAddProduct, tfAddProductQuantity, 
				tfAddUOM, btnSubmitProduct, btnDeleteProduct);
		
		return hl_product_row;
	}
	

	public void delete () {
		recipesDB.delete(recipe);
		AllRecipesView.updateList();
		setVisible(false);
	}
	
	public Recipe addRecipe () {
		recipe = new Recipe();
		recipe.setName(name.getValue());
		recipe.setDesc(desc.getValue());
		recipe.setUserId(1);
		
		return recipe;
	}
	
	
	public List<EquipmentUsage> addEquipmentUsages (long id) {
		List<EquipmentUsage> eqUsages = new ArrayList<>();
		
		for (String eqUsage : equipmentUsed) {
			
			
			EquipmentUsage eUsage = new EquipmentUsage();
			
		
			eUsage.setEquipment(new Equipment(eqUsage));
			eUsage.setRecipeIdd(id);
			
			eqUsages.add(eUsage);
		}
		 
		return eqUsages;
	}
	
	public List<ProductUsage> addProductUsages (long id) {
		List<ProductUsage> prodUsages = new ArrayList<>();
		
		for (String productUsed : productsUsed) {
			ProductUsage prodUsage = new ProductUsage();
			
			prodUsage.setProduct(new Product(productUsed));
			prodUsage.setRecipeIdd(id);
		
			prodUsages.add(prodUsage);
		}
		
		return prodUsages;
		
	}
	
	
	public void save () {
		Recipe recipee = addRecipe();
		
		
		
		recipesDB.save(recipe);
		
		recipeServices.addRecipe(recipee);
		
		List<EquipmentUsage> eqUsages = addEquipmentUsages(recipee.getId());
		List<ProductUsage> prodUsages = addProductUsages(recipee.getId());
		
		for (EquipmentUsage eqUsage : eqUsages) {
			recipeServices.addEquipmentUsage(eqUsage);
		}
		
		
		for (ProductUsage prodUsage : prodUsages) {
			recipeServices.addProductUsage(prodUsage);
		}
		
		
		this.addComponent(new Label("Recipe successfully added!"));
	}
}