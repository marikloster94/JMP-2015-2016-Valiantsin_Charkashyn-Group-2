package com.epam.builder.car;

import java.util.List;

import com.epam.builder.body.BodyBuilder;
import com.epam.builder.body.HatchbackBodyBuilder;
import com.epam.builder.engine.BenzineEngineBuilder;
import com.epam.builder.engine.EngineBuilder;
import com.epam.builder.headlight.FilamentHeadlightBuilder;
import com.epam.builder.headlight.HalogenHeadlightBuilder;
import com.epam.builder.headlight.HeadlightBuilder;
import com.epam.builder.interior.InteriorBuilder;
import com.epam.builder.interior.VelourInteriorBuilder;
import com.epam.builder.transmission.ManualTransmissionBuilder;
import com.epam.builder.transmission.TransmissionBuilder;
import com.epam.builder.weel.SummerWeelBuilder;
import com.epam.builder.weel.WeelBuilder;
import com.epam.entity.Headlight;
import com.epam.entity.Weel;

public class HatchbackCarBuilder extends CarBuilder {

	@Override
	public void buildCarBody() {
		BodyBuilder builder = new HatchbackBodyBuilder();
		builder.buildBody();
		builder.buildAmountDoor();
		builder.buildBodyType();
		builder.buildColor();
		car.setBody(builder.getBody());
	}

	@Override
	public void buildEngine() {
		EngineBuilder builder = new BenzineEngineBuilder();
		builder.buildEngine();
		builder.buildFuelType();
		builder.buildPower();
		builder.buildVolume();
		car.setEngine(builder.getEngine());
	}

	@Override
	public void buildInterior() {
		InteriorBuilder builder = new VelourInteriorBuilder();
		builder.buildInterior();
		builder.buildColor();
		builder.buildMaterial();
		car.setInterior(builder.getInterior());
	}

	@Override
	public void buildTransmission() {
		TransmissionBuilder builder = new ManualTransmissionBuilder();
		builder.buildTransmission();
		builder.buildTransmissionType();
		builder.buildAmountUnit();
		car.setTransmission(builder.getTransmission());
	}

	@Override
	public void buildFrontHeadlights() {
		List<Headlight> lights = car.getLights();
		HeadlightBuilder builder = new HalogenHeadlightBuilder();
		builder.buildHeadlight();
		builder.builLampColor();
		builder.builLampType();
		lights.add(builder.getLight());

	}

	@Override
	public void buildBackHeadlights() {
		List<Headlight> lights = car.getLights();
		HeadlightBuilder builder = new FilamentHeadlightBuilder();
		builder.buildHeadlight();
		builder.builLampColor();
		builder.builLampType();
		lights.add(builder.getLight());

	}

	@Override
	public void buildWeels() {
		WeelBuilder builder = new SummerWeelBuilder();
		List<Weel> weels = car.getWeels();
		builder.buildWeel();
		builder.buildWeelType();
		if (weels.size() < 4) {
			weels.add(builder.getWeel());
		}
	}

}
