package com.epam.model;

import javax.swing.JFrame;

import com.epam.bridge.PresentationAPI;

public abstract class AbstractList {

	protected PresentationAPI api;

	public AbstractList(PresentationAPI api){
		this.api = api;
	}
	
	public abstract void presentateList(JFrame frame);
	
	
	
}
