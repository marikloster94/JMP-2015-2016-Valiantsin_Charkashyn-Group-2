package com.epam.builder.body;

import com.epam.entity.CarBody;

public abstract class BodyBuilder {

	protected CarBody body;
	
	public void buildBody(){
		body = new CarBody();
	}
	
	public CarBody getBody(){
		return body;
	}
	
	public abstract void buildColor();
	public abstract void buildBodyType();
	public abstract void buildAmountDoor();
}
