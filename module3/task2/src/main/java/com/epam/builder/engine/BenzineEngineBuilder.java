package com.epam.builder.engine;

public class BenzineEngineBuilder extends EngineBuilder {
	
	@Override
	public void buildPower() {
		engine.setPower(140);
	}

	@Override
	public void buildFuelType() {
		engine.setFuelType("benzine");
	}

	@Override
	public void buildVolume() {
		engine.setVolume(1.6);
	}
}
