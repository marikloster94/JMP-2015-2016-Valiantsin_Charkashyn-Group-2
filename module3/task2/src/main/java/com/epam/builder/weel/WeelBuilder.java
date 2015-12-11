package com.epam.builder.weel;

import com.epam.entity.Weel;

public abstract class WeelBuilder {

	protected Weel weel;
	
	public void buildWeel(){
		weel = new Weel();
	}
	
	public Weel getWeel(){
		return weel;
	}
	
	public abstract void buildWeelType();
}
