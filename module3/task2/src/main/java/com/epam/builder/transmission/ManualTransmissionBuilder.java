package com.epam.builder.transmission;

public class ManualTransmissionBuilder extends TransmissionBuilder {

	@Override
	public void buildTransmissionType() {
		transmission.setTransmissionType("manual");
	}

	@Override
	public void buildAmountUnit() {
		transmission.setAmountUnit(6);
	}

}
