package com.epam.builder.car;

import com.epam.entity.Car;

public abstract class CarBuilder {

	protected Car car;
	public void buildCar(){
		car =  new Car();
	}
	
	public Car getCar(){
		return car;
	}
	
	public abstract void buildCarBody();
	public abstract void buildEngine();
	public abstract void buildInterior();
	public abstract void buildTransmission();
	public abstract void buildFrontHeadlights();
	public abstract void buildBackHeadlights();
	public abstract void buildWeels();
}
