package com.example.recipes_frontend.client_service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.recipes_frontend.modelUI.Recipe;




public class RecipeService {

	private static RecipeService instance;
	private static final Logger LOGGER = Logger.getLogger(RecipeService.class.getName());

	private final HashMap<Long, Recipe> recipes = new HashMap<>();
	private long nextId = 0;

	private RecipeService() {
	}

	/**
	 * @return a reference to an example facade for Customer objects.
	 */
	public static RecipeService getInstance() {
		if (instance == null) {
			instance = new RecipeService();
			instance.ensureTestData();
		}
		return instance;
	}
	
	public synchronized List<Recipe> findAll() {
		return findAll(null);
	}
	
	public synchronized List<Recipe> findAll(String stringFilter) {
		ArrayList<Recipe> arrayList = new ArrayList<>();
		for (Recipe recipe : recipes.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| recipe.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add((Recipe) recipe.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Recipe>() {

			@Override
			public int compare(Recipe o1, Recipe o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}
	
	public synchronized List<Recipe> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Recipe> arrayList = new ArrayList<>();
		for (Recipe recipe : recipes.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| recipe.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(recipe.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(RecipeService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Recipe>() {

			@Override
			public int compare(Recipe o1, Recipe o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}
	
	public synchronized long count() {
		return recipes.size();
	}

	
	public synchronized void delete(Recipe value) {
		recipes.remove(value.getId());
	}

	
	public synchronized void save(Recipe entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Customer is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Recipe) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		recipes.put(entry.getId(), entry);
	}
	
	public void ensureTestData() {
		if (findAll().isEmpty()) {
			final String[] names = new String[] { "mandja", "kartofi", "supa",
					"lazanq", "pica"};
			
			for (String name : names) {
				Recipe c = new Recipe();
				c.setName(name);
				c.setDesc(name);
				c.setUserId(0);
				save(c);
			}
		}
	}
}
