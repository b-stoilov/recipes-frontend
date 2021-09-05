package com.example.recipes_frontend.client_service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.atmosphere.inject.annotation.ApplicationScoped;
import org.jsoup.safety.Cleaner;

import com.example.recipes_frontend.modelUI.EquipmentUsage;
import com.example.recipes_frontend.modelUI.ProductUsage;
import com.example.recipes_frontend.modelUI.Recipe;
import com.example.recipes_frontend.modelUI.RecipeStep;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class RecipeServices {
    

    private Client client;
    private WebTarget target;
    private WebTarget targetRecipeStep;
    private WebTarget targetEquipmentUsage;
    private WebTarget targetProductUsage;
    ObjectMapper mapper;

    @PostConstruct
    protected void init() {
    	mapper = new ObjectMapper();
        client = ClientBuilder.newClient();
        
        target = client.target("http://localhost:8080/api");
        targetRecipeStep = client.target("http://localhost:8080/api/recipe-step");
        targetEquipmentUsage = client.target("http://localhost:8080/api/equipment-usage");
        targetProductUsage = client.target("http://localhost:8080/api/product-usage");
    }
    
    //GET REQUESTS !!!

	public List<Recipe> getAllRecipes() {
		init();

		
		String response = target
				.path("recipes")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
		

		InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
		JavaType type = mapper.getTypeFactory().
				  constructCollectionType(List.class, Recipe.class);
		
		List<Recipe> recipes = null;
		
		try {
			recipes = mapper.readValue(stream, type);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
        return recipes;
    }
	
	
	public List<RecipeStep> getRecipeStepsByRecipeId (long id) {
		String response = targetRecipeStep
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		
		InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
		JavaType type = mapper.getTypeFactory().
				  constructCollectionType(List.class, RecipeStep.class);
		
		List<RecipeStep> recipeSteps = null;
		

		try {
			recipeSteps = mapper.readValue(stream, type);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
        return recipeSteps;
	}
	
	public List<EquipmentUsage> getEqUsagesPerRecipe (long id) {
		init();
		
		String response = targetEquipmentUsage
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		
		InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
		JavaType type = mapper.getTypeFactory().
				  constructCollectionType(List.class, EquipmentUsage.class);
		
		List<EquipmentUsage> eqUsages = null;
	
		try {
			eqUsages = mapper.readValue(stream, type);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eqUsages;
	}
	
	public List<ProductUsage> getProdusagesPerRecipe (long id) {
		init();
		
		String response = targetProductUsage
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		
		InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
		JavaType type = mapper.getTypeFactory().
				  constructCollectionType(List.class, ProductUsage.class);
		
		List<ProductUsage> prodUsages = null;
	
		try {
			prodUsages = mapper.readValue(stream, type);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prodUsages;
	}
	
	
	//POST REQUESTS
	
	public Response addRecipe(Recipe recipe) {
		init();
		Response response = null;
		String responseString = null;
		Recipe altRecipe = null;
		
		try {
			response = target
				.path("recipes")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(recipe, MediaType.APPLICATION_JSON));
			
			responseString = response.readEntity(String.class);
		
			altRecipe = mapper.readValue(responseString, Recipe.class);
			
			recipe.setId(altRecipe.getId());
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return response;
	}
	
	public Response addEquipmentUsage(EquipmentUsage eqUsage) {
		init();
		
		Response response = null;
		String responseString = null;
		
		try {
			response = target
				.path("equipment-usage")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(eqUsage, MediaType.APPLICATION_JSON));
			
			responseString = response.readEntity(String.class);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return response;
	}
	
	public Response addProductUsage (ProductUsage prodUsage) {
		init();
		
		Response response = null; 
		String responseString = null;
	
		try {
			response = target
				.path("product-usage")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(prodUsage, MediaType.APPLICATION_JSON));
			
			responseString = response.readEntity(String.class);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return response;
	
	}
	
	public Response addRecipeStep (RecipeStep recipeStep) {
		init();
		
		Response response = null; 
		String responseString = null;
		try {
			response = target
				.path("recipe-step")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(recipeStep, MediaType.APPLICATION_JSON));
			
			responseString = response.readEntity(String.class);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
 		return response;
	}
	

}