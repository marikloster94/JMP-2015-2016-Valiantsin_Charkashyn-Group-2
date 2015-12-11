package com.epam.builder.interior;

import com.epam.entity.Interior;

public abstract class InteriorBuilder {
	protected Interior interior;

	public void buildInterior() {
		interior = new Interior();
	}
	
	public Interior getInterior(){
		return interior;
	}

	public abstract void buildColor();

	public abstract void buildMaterial();
}
