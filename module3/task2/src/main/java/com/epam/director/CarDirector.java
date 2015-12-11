package com.epam.director;

import com.epam.builder.car.CarBuilder;
import com.epam.entity.Car;

public class CarDirector {
	private CarBuilder carBuilder = null;

	public CarDirector(CarBuilder carBuilder) {
		this.carBuilder = carBuilder;
	}

	public void constructCar() {
		carBuilder.buildCar();
		carBuilder.buildCarBody();
		carBuilder.buildEngine();
		carBuilder.buildTransmission();
		carBuilder.buildHeadlights();
		carBuilder.buildInterior();
		for(int i=0; i<4; i++){
			carBuilder.buildWeels();
		}
		
	}

	public Car getCar() {
		return carBuilder.getCar();
	}
}
