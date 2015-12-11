package com.epam.entity;

public class Interior {

	private String interiorMaterial;
	private String interiorColor;

	public Interior() {

	}

	public String getInteriorMaterial() {
		return interiorMaterial;
	}

	public void setInteriorMaterial(String interiorMaterial) {
		this.interiorMaterial = interiorMaterial;
	}

	public String getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}

	@Override
	public String toString() {
		return "Interior [interiorMaterial=" + interiorMaterial + ", interiorColor=" + interiorColor + "]";
	}

}
