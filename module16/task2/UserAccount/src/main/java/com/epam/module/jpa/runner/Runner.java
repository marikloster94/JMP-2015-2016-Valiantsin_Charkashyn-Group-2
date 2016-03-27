package com.epam.module.jpa.runner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.module.jpa.service.AccountService;
import com.epam.module.jpa.service.PersonService;

public class Runner {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("hibernate-conf.xml");
		AccountService accServ = ctx.getBean(AccountService.class);
		PersonService personServ = ctx.getBean(PersonService.class);
		if (accServ == null || personServ == null) {
			System.err.println("Error! Service can not be null");
		}else{
			AppHelper.workWithMenu(accServ, personServ);
		}

	}

}
