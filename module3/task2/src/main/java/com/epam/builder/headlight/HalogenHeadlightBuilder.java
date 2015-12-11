package com.epam.builder.headlight;

public class HalogenHeadlightBuilder extends HeadlightBuilder {

	@Override
	public void builLampColor() {
		light.setLampColor("red");
	}

	@Override
	public void builLampType() {
		light.setLampType("halogen");
	}

}
