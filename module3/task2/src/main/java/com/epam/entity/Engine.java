package com.epam.entity;

public class Engine {

	private int power;
	private String fuelType;
	private double volume;

	public Engine() {

	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Engine [power=" + power + ", fuelType=" + fuelType + ", volume=" + volume + "]";
	}

}
