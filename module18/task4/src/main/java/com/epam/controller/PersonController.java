package com.epam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.epam.service.PersonService;

@Controller
public class PersonController {
	
	private static final Logger log = Logger.getLogger(PersonController.class);
	
	@Autowired
	PersonService service;
}
