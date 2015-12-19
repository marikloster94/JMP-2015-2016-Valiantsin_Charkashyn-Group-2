package com.epam.model;

public class Square extends Figure {

	protected double height;

	public Square(String name, double height) {
		super(name);
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public double calculateSquare() {
		return height * height;

	}

}
