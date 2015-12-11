package com.epam.entity;

public class CarBody {

	private String color;
	private int doorAmount;
	private String bodyType;
	
	public CarBody(){
		
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getDoorAmount() {
		return doorAmount;
	}
	public void setDoorAmount(int doorAmount) {
		this.doorAmount = doorAmount;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	@Override
	public String toString() {
		return "CarBody [color=" + color + ", doorAmount=" + doorAmount + ", bodyType=" + bodyType + "]";
	}
	
	
}
