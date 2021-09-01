package com.example.recipes_frontend;

import javax.servlet.annotation.WebServlet;

import com.example.recipes_frontend.client_service.RecipeServices;
import com.example.recipes_frontend.modelUI.Recipe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import com.example.recipes_frontend.StartView;


@Theme("mytheme")
public class MyUI extends UI {
	Navigator navigator;
	protected static final String MAINVIEW = "home";


	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("Menu navigation");
    	
    	navigator = new Navigator(this, this);
    	
//    	navigator.addView("", new StartView(navigator, MAINVIEW));
    	navigator.addView(MAINVIEW, new MainView(navigator, MAINVIEW));

    	
    }
    
    
    
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
