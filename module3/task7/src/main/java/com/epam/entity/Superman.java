package com.epam.entity;

public class Superman {

	private static final Superman instance = new Superman();
	private String name;

	private Superman() {
		setName("Batman");
	}

	public static Superman getInstance() {
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
