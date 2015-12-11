package com.epam.builder.headlight;

public class FilamentHeadlightBuilder extends HeadlightBuilder{

	@Override
	public void builLampColor() {
		light.setLampColor("white");
	}

	@Override
	public void builLampType() {
		light.setLampType("filament");
	}


}
