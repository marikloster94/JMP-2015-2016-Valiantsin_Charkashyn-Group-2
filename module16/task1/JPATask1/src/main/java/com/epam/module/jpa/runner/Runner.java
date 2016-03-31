package com.epam.module.jpa.runner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"hibernate-conf.xml");
		AppHelper.menu(ctx);

	}

}
