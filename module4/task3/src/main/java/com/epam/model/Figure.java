package com.epam.model;

public abstract class Figure {

	protected String name;

	public abstract double calculateSquare();

	public Figure(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
