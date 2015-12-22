package com.epam.flyweight;

import com.epam.factory.ListFactory;
import com.epam.model.List;

public class FlyWeight {
	private static final String diretory = "user.home";
	
	private ListFactory factory;
	private List directoryList;
	
	public FlyWeight(ListFactory factory){
		this.factory = factory;
	}

	public static String getDiretory() {
		return diretory;
	}

	public ListFactory getFactory() {
		return factory;
	}

	public void setFactory(ListFactory factory) {
		this.factory = factory;
	}

	public List getDirectoryList() {
		return directoryList;
	}

	public void setDirectoryList(List directoryList) {
		this.directoryList = directoryList;
	}
	
	
}
