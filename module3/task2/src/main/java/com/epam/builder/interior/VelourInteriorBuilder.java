package com.epam.builder.interior;

public class VelourInteriorBuilder extends InteriorBuilder {

	@Override
	public void buildColor() {
		interior.setInteriorColor("grey");
	}

	@Override
	public void buildMaterial() {
		interior.setInteriorMaterial("velour");
	}

}
