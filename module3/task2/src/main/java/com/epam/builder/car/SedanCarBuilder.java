package com.epam.builder.car;

import java.util.List;

import com.epam.builder.body.BodyBuilder;
import com.epam.builder.body.SedanBodyBuilder;
import com.epam.builder.engine.DieselEngineBuilder;
import com.epam.builder.engine.EngineBuilder;
import com.epam.builder.headlight.HalogenHeadlightBuilder;
import com.epam.builder.headlight.HeadlightBuilder;
import com.epam.builder.interior.InteriorBuilder;
import com.epam.builder.interior.LeatherInteriorBuilder;
import com.epam.builder.transmission.AutomaticTransmissionBuilder;
import com.epam.builder.transmission.TransmissionBuilder;
import com.epam.builder.weel.WeelBuilder;
import com.epam.builder.weel.WinterWeelBuilder;
import com.epam.entity.Headlight;
import com.epam.entity.Weel;

public class SedanCarBuilder extends CarBuilder {

	@Override
	public void buildCarBody() {
		BodyBuilder builder = new SedanBodyBuilder();
		builder.buildBody();
		builder.buildAmountDoor();
		builder.buildBodyType();
		builder.buildColor();
		car.setBody(builder.getBody());
	}

	@Override
	public void buildEngine() {
		EngineBuilder builder = new DieselEngineBuilder();
		builder.buildEngine();
		builder.buildFuelType();
		builder.buildPower();
		builder.buildVolume();
		car.setEngine(builder.getEngine());
	}

	@Override
	public void buildInterior() {
		InteriorBuilder builder = new LeatherInteriorBuilder();
		builder.buildInterior();
		builder.buildColor();
		builder.buildMaterial();
		car.setInterior(builder.getInterior());
	}

	@Override
	public void buildTransmission() {
		TransmissionBuilder builder = new AutomaticTransmissionBuilder();
		builder.buildTransmission();
		builder.buildTransmissionType();
		builder.buildAmountUnit();
	}

	@Override
	public void buildHeadlights() {
		List<Headlight> lights = car.getLights();
		HeadlightBuilder builder = new HalogenHeadlightBuilder();
		builder.buildHeadlight();
		builder.builLampColor();
		builder.builLampType();
		lights.add(builder.getLight());
		car.setLights(lights);
		
	}

	@Override
	public void buildWeels() {
		WeelBuilder builder = new WinterWeelBuilder();
		List<Weel> weels = car.getWeels();
		builder.buildWeel();
		builder.buildWeelType();
		if(weels.size()<4){
			weels.add(builder.getWeel());
		}
	}

}
