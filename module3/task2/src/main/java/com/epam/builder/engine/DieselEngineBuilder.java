package com.epam.builder.engine;

public class DieselEngineBuilder extends EngineBuilder {

	@Override
	public void buildPower() {
		engine.setPower(120);

	}

	@Override
	public void buildFuelType() {
		engine.setFuelType("diesel");
	}

	@Override
	public void buildVolume() {
		engine.setVolume(2.0);
	}

}
