package com.example.recipes_frontend.modelUI;

import java.io.Serializable;

import javax.persistence.Column;

public class RecipeStepId implements Serializable {
    
	
	private long id;

    
	@Column(name = "seq")
    private long sequence;
	
	
	public RecipeStepId () {}

	public RecipeStepId(long id, long sequence) {
		this.id = id;
		this.sequence = sequence;
	}


	public long getId() {
		return id;
	}


	public long getSequence() {
		return sequence;
	}


	
}