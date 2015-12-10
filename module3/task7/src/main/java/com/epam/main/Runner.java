package com.epam.main;

import com.epam.entity.Superman;

public class Runner {

	public static void main(String[] args) {
		System.out.println("Create Superman");
		Superman man = Superman.getInstance();
		System.out.println("Superman " + man.getName() + " created");
	}

}
