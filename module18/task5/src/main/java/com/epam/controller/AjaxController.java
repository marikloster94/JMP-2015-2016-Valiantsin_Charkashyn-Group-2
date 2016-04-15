package com.epam.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.Booking;
import com.epam.model.ShowTime;
import com.epam.model.ajax.AjaxResponseBody;
import com.epam.model.ajax.SearchCriteria;
import com.epam.model.ajax.Views;
import com.epam.service.BookingService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class AjaxController {
	
	private static final Logger log = Logger.getLogger(BookingController.class);

	@Autowired
	private BookingService service;
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/getSearchResult", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) {
		List<Integer> freePlaces = new ArrayList<Integer>();
		for (int i = 1; i <= 15; i++) {
			freePlaces.add(i);
		}
		AjaxResponseBody result = new AjaxResponseBody();
		try {
			Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(search.getDate());
			List<Booking> bookings = service.get(bookingDate);
			Map<String, List<Integer>> showTimes = new HashMap<String, List<Integer>>();
			if (bookings == null || bookings.isEmpty()) {
				result.setCode("204");
				result.setMsg("No bookings. Can not form showtimes");
				return result;
			}
			for (Booking booking : bookings) {
				System.out.println(booking.getBookingDate());
				String key = new StringBuilder(booking.getMovie()).append("/").append(booking.getBookingDate()).toString();
				List<Integer> places = freePlaces;
				if(showTimes.containsKey(key)){
					places = showTimes.get(key);
				}
				places.remove((Integer)booking.getPlace());
				showTimes.put(key, places);
			}
			List<ShowTime> showtimes = new ArrayList<ShowTime>();
			for (String key: showTimes.keySet()) {
				ShowTime showTime = new ShowTime();
				String []keyValues = key.split("/");
				showTime.setMovie(keyValues[0]);
				showTime.setDate(keyValues[1]);
				showTime.setPlaces(showTimes.get(key));
				showtimes.add(showTime);
			}
			result.setResult(showtimes);
			result.setCode("200");
			result.setMsg("");
		} catch (ParseException e) { 
			log.error(AjaxController.class, e);
			result.setCode("204");
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			log.error(AjaxController.class, e);
			result.setCode("204");
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
}
