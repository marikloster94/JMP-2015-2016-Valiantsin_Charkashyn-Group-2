package com.epam.builder.transmission;

import com.epam.entity.Transmission;

public abstract class TransmissionBuilder {
	protected Transmission transmission;

	public void buildTransmission() {
		transmission = new Transmission();
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public abstract void buildTransmissionType();

	public abstract void buildAmountUnit();
}
