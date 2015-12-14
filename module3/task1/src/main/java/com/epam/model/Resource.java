package com.epam.model;


public abstract class Resource{

	private String resourceName;

	public Resource() {

	}

	public void serResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceName() {
		return this.resourceName;
	}

}
