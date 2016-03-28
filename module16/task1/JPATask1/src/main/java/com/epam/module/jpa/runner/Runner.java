package com.epam.module.jpa.runner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.module.jpa.service.EmployeeService;

public class Runner {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("hibernate-conf.xml");
		EmployeeService personServ = ctx.getBean(EmployeeService.class);
		if (personServ == null) {
			System.err.println("Error! Service can not be null");
		}else{
//			AppHelper.workWithMenu(accServ, personServ);
		}

	}

}
