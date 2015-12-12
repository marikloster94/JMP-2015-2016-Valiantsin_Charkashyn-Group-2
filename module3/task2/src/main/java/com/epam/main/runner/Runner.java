package com.epam.main.runner;

import com.epam.builder.car.HatchbackCarBuilder;
import com.epam.builder.car.SedanCarBuilder;
import com.epam.director.CarDirector;
import com.epam.entity.Car;

public class Runner {

	public static void main(String[] args) {
		CarDirector carDirector = new CarDirector(new SedanCarBuilder());
		Car car = carDirector.constructCar().getCar();
		System.out.println("Director built the first car:");
		System.out.println(car);
		carDirector =  new CarDirector(new HatchbackCarBuilder());
		car = carDirector.constructCar().getCar();
		System.out.println("Director built the second car:");
		System.out.println(car);
	}

}
