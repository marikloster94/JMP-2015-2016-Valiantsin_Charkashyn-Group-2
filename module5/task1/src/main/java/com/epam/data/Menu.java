package com.epam.data;

import org.apache.log4j.Logger;

public class Menu {

	private final static Logger logger = Logger.getLogger(Menu.class);
	
	public static void showMenu(){
		System.out.println("Menu:");
		System.out.println("1 - create New Year holiday");
		System.out.println("2 - create you own holiday");
		System.out.println("3 - show holiday");
		System.out.println("4 - exit");
		System.out.println("Please choose: ");
	}
	
}
