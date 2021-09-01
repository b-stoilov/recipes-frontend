package com.example.recipes_frontend;


import com.example.recipes_frontend.modelUI.Recipe;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.declarative.Design;

@DesignRoot
public class MainView extends VerticalLayout implements View {
	Navigator navigator;
	String MAINVIEW;
	
	
	class ButtonListener implements Button.ClickListener {
		String menuItem;
		
		public ButtonListener (String menuItem) {
			this.menuItem = menuItem;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			navigator.navigateTo(MAINVIEW + "/" + menuItem); 
			
		}
	}
	
	VerticalLayout menuContent;
    Panel panel;
    Button back;
	
	public MainView (Navigator navigator, String MAINVIEW) {
		this.navigator = navigator;
		this.MAINVIEW = MAINVIEW;
		
		Design.read(this);
		Button btnHome = new Button("Home");
		
		
		
		menuContent.addComponent(btnHome);
		menuContent.addComponent(new Button("Add a recipe", 
				new ButtonListener("add-a-recipe")));
		
		btnHome.addClickListener(click -> navigator.navigateTo("home"));
		back.addClickListener(click -> navigator.navigateTo("signIn"));
			
	}
	
	@Override
	public void enter (ViewChangeEvent event) {
		if (event.getParameters() == null || event.getParameters().isEmpty()) {
	
			panel.setContent(new AllRecipesView(panel)); 
				
		} else {
			panel.setContent(new AddRecipeView(panel));
		}
	}
}