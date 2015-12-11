package com.epam.builder.interior;

public class LeatherInteriorBuilder extends InteriorBuilder {

	@Override
	public void buildColor() {
		interior.setInteriorColor("black");
	}

	@Override
	public void buildMaterial() {
		interior.setInteriorMaterial("leather");
	}

}
