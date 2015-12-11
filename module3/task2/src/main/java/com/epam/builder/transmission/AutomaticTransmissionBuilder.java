package com.epam.builder.transmission;

public class AutomaticTransmissionBuilder extends TransmissionBuilder {

	@Override
	public void buildTransmissionType() {
		transmission.setTransmissionType("automatic");
	}

	@Override
	public void buildAmountUnit() {
		transmission.setAmountUnit(5);
	}

}
