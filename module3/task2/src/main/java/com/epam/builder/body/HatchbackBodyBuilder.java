package com.epam.builder.body;


public class HatchbackBodyBuilder extends BodyBuilder {

	@Override
	public void buildColor() {
		body.setColor("green");
	}

	@Override
	public void buildBodyType() {
		body.setBodyType("Hatchback");
	}

	@Override
	public void buildAmountDoor() {
		body.setDoorAmount(3);
	}

}
