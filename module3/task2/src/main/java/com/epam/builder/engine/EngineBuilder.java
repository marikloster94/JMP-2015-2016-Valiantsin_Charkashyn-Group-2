package com.epam.builder.engine;

import com.epam.entity.Engine;

public abstract class EngineBuilder {

	protected Engine engine;
	
	public void buildEngine(){
		engine = new Engine();
	}
	
	public Engine getEngine(){
		return engine;
	}
	
	public abstract void buildPower();
	public abstract void buildFuelType();
	public abstract void buildVolume();
}
