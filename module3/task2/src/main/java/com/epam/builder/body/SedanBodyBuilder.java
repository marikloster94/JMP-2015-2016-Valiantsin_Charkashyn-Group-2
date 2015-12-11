package com.epam.builder.body;


public class SedanBodyBuilder extends BodyBuilder {
	
	@Override
	public void buildColor() {
		body.setColor("blue");
	}

	@Override
	public void buildBodyType() {
		body.setBodyType("Sedan");
	}

	@Override
	public void buildAmountDoor() {
		body.setDoorAmount(5);
	}

}
