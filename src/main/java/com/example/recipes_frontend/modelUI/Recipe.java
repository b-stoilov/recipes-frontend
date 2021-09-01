package com.example.recipes_frontend.modelUI;

import java.io.Serializable;

public class Recipe implements Serializable, Cloneable {
	private Long id;
	private String name;
	private String desc;
	private long userId;
	
	
	public Recipe(long id, String name, String desc, long userId) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.userId = userId;
	}

	public Recipe() {
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Recipe && obj.getClass().equals(getClass())) {
			return this.id.equals(((Recipe) obj).id);
		}

		return false;
	}
	
	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Recipe clone() throws CloneNotSupportedException {
		return (Recipe) super.clone();
	}

	

	

}
