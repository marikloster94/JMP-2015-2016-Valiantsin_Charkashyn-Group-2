package com.epam.entity;

import java.util.ArrayList;
import java.util.List;

public class Car {

	private CarBody body;
	private Engine engine;
	private Interior interior;
	private Transmission transmission;
	private List<Weel> weels;
	private List<Headlight> lights;
	
	public Car(){
		lights = new ArrayList<Headlight>();
		weels = new ArrayList<Weel>();
	}

	public CarBody getBody() {
		return body;
	}

	public void setBody(CarBody body) {
		this.body = body;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Interior getInterior() {
		return interior;
	}

	public void setInterior(Interior interior) {
		this.interior = interior;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public List<Weel> getWeels() {
		return weels;
	}

	public void setWeels(List<Weel> weels) {
		this.weels = weels;
	}

	public List<Headlight> getLights() {
		return lights;
	}

	public void setLights(List<Headlight> lights) {
		this.lights = lights;
	}

	@Override
	public String toString() {
		return "Car [body=" + body + ", engine=" + engine + ", interior=" + interior + ", transmission=" + transmission + ", weels=" + weels + ", lights="
				+ lights + "]";
	}
	
	
}
