package com.epam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.epam.service.BookingService;


@Controller
public class BookingController {

	private static final Logger log = Logger.getLogger(BookingController.class);
	
	@Autowired
	private BookingService service;
	
}
