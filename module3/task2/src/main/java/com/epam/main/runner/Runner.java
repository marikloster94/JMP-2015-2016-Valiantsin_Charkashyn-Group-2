package com.epam.main.runner;

import com.epam.builder.car.CarBuilder;
import com.epam.builder.car.SedanCarBuilder;
import com.epam.director.CarDirector;
import com.epam.entity.Car;

public class Runner {

	public static void main(String[] args) {
		CarBuilder carBuilder = new SedanCarBuilder();
		CarDirector carDirector = new CarDirector(carBuilder);
		carDirector.constructCar();
		Car car = carDirector.getCar();
		System.out.println(car);
	}

}
