package com.epam.model;

public class Circle extends Figure {

	private double radius;
	
	public Circle(String name, double raduis){
		super(name);
		this.radius = raduis;
	}
	
	@Override
	public double calculateSquare() {
		return Math.PI*radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
