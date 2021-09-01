package com.example.recipes_frontend;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class StartView extends VerticalLayout implements View {
	Navigator navigator;
	String MAINVIEW;
	
	public StartView (Navigator navigator, String MAINVIEW) {
		this.navigator = navigator;
		this.MAINVIEW = MAINVIEW;
		
		setSizeFull();
		
		Button button = new Button("Enter");
		
		button.addClickListener(click -> navigator.navigateTo(MAINVIEW));
		
		addComponent(button);
		setComponentAlignment(button, Alignment.MIDDLE_CENTER);
	}
	
	@Override
	public void enter (ViewChangeEvent event) {
		Notification.show("Welcome to the Cooking Recipes Forum");
	}


}
