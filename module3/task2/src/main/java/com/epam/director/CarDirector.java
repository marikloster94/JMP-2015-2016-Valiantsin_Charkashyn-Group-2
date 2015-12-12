package com.epam.director;

import com.epam.builder.car.CarBuilder;
import com.epam.entity.Car;

public class CarDirector {
	private CarBuilder carBuilder = null;

	public CarDirector(CarBuilder carBuilder) {
		this.carBuilder = carBuilder;
	}

	public CarBuilder constructCar() {
		carBuilder.buildCar();
		carBuilder.buildCarBody();
		carBuilder.buildEngine();
		carBuilder.buildTransmission();
		for (int i = 0; i < 2; i++) {
			carBuilder.buildFrontHeadlights();
		}
		for (int i = 0; i < 2; i++) {
			carBuilder.buildBackHeadlights();
		}
		carBuilder.buildInterior();
		for (int i = 0; i < 4; i++) {
			carBuilder.buildWeels();
		}
		return carBuilder;

	}

	public Car getCar() {
		return carBuilder.getCar();
	}
}
