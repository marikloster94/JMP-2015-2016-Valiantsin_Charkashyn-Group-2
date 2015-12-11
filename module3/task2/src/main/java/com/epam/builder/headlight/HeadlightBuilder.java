package com.epam.builder.headlight;
import com.epam.entity.Headlight;


public abstract class HeadlightBuilder {

	protected Headlight light;
	
	public void buildHeadlight(){
		light = new Headlight();
	}
	
	public Headlight getLight(){
		return light;
	}
	
	public abstract void builLampColor();
	public abstract void builLampType();
}
