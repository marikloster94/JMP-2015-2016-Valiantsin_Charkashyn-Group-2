package com.epam.entity;

public class Headlight {

	private String lampType;
	private String lampColor;
	
	public Headlight(){
		
	}

	public String getLampType() {
		return lampType;
	}

	public void setLampType(String lampType) {
		this.lampType = lampType;
	}

	public String getLampColor() {
		return lampColor;
	}

	public void setLampColor(String lampColor) {
		this.lampColor = lampColor;
	}

	@Override
	public String toString() {
		return "Headlight [lampType=" + lampType + ", lampColor=" + lampColor + "]";
	}

}
