package com.epam.model;

public class Rectangle extends Square {

	private double width;

	public Rectangle(String name, double height, double width) {
		super(name, height);
		this.width = width;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public double calculateSquare() {
		return width * height;
	}

}
