package com.epam.builder.car;

import java.util.List;

import com.epam.builder.body.BodyBuilder;
import com.epam.builder.engine.EngineBuilder;
import com.epam.builder.headlight.HeadlightBuilder;
import com.epam.builder.interior.InteriorBuilder;
import com.epam.builder.transmission.TransmissionBuilder;
import com.epam.builder.weel.WeelBuilder;
import com.epam.entity.Car;
import com.epam.entity.Headlight;
import com.epam.entity.Weel;

@Deprecated
public class CarClassBuilder {
	private Car car;

	public void buildCar() {
		car = new Car();
	}
	
	public Car getCar(){
		return car;
	}

	public void buildCarBody(BodyBuilder builder) {
		// BodyBuilder builder = new SedanBodyBuilder();
		builder.buildBody();
		builder.buildAmountDoor();
		builder.buildBody();
		builder.buildBodyType();
		builder.buildColor();
		car.setBody(builder.getBody());
	}

	public void buildEngine(EngineBuilder builder) {
		// EngineBuilder builder = new DieselEngineBuilder();
		builder.buildEngine();
		builder.buildFuelType();
		builder.buildPower();
		builder.buildVolume();
		car.setEngine(builder.getEngine());
	}

	public void buildInterior(InteriorBuilder builder) {
		// InteriorBuilder builder = new LeatherInteriorBuilder();
		builder.buildInterior();
		builder.buildColor();
		builder.buildMaterial();
		car.setInterior(builder.getInterior());
	}

	public void buildTransmission(TransmissionBuilder builder) {
//		TransmissionBuilder builder = new AutomaticTransmissionBuilder();
		builder.buildTransmission();
		builder.buildAmountUnit();
		builder.buildTransmissionType();
		car.setTransmission(builder.getTransmission());
	}

	public void buildHeadlights(HeadlightBuilder builder) {
		List<Headlight> lights = car.getLights();
		builder.buildHeadlight();
		builder.builLampColor();
		builder.builLampType();
		lights.add(builder.getLight());
	}

	public void buildWeels(WeelBuilder builder) {
		List<Weel> weels = car.getWeels();
		builder.buildWeel();
		builder.buildWeelType();
		if(weels.size()<4){
			weels.add(builder.getWeel());
		}
	}
}
