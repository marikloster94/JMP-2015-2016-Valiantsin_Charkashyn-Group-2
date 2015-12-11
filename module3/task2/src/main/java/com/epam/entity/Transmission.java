package com.epam.entity;

public class Transmission {

	private String transmissionType;
	private int amountUnit;

	public Transmission() {

	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public int getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(int amountUnit) {
		this.amountUnit = amountUnit;
	}

	@Override
	public String toString() {
		return "Transmission [transmissionType=" + transmissionType + ", amountUnit=" + amountUnit + "]";
	}

}
