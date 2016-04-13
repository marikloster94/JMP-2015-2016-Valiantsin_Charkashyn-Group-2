package com.epam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.model.Booking;
import com.epam.service.BookingService;

@Controller
public class AjaxController {
	
	private static final Logger log = Logger.getLogger(BookingController.class);

	@Autowired
	private BookingService service;
	
	public AjaxResponseBody getSearchResultViaAjax(@RequestBody Booking search) {
		return null;
	}
}
