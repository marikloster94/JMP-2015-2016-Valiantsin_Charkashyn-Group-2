package com.epam.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.model.Booking;
import com.epam.model.Person;
import com.epam.service.BookingService;
import com.epam.service.PersonService;


@Controller
public class BookingController {

	private static final Logger log = Logger.getLogger(BookingController.class);
	
	@Autowired
	private BookingService service;
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/newBooking" , method = RequestMethod.GET)
	public String goToAddBookingPage(Model model){
		String result = "newBooking";
		try {
			List<Person> people = personService.getAll();
			if(people.isEmpty()){
				model.addAttribute("error", "No data for add booking");
				return "error";
			}
			model.addAttribute("persons", people);
		} catch (Exception e) {
			log.error(Booking.class, e);
			model.addAttribute("error", "No data for add booking");
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/addBooking" , method = RequestMethod.POST)
	public String addBooking(@ModelAttribute("booking") Booking booking, @ModelAttribute("person") int idPerson,Model model){
		String result = "index";
		try {
			Person person = personService.get(idPerson);
			booking.setClient(person);
			service.add(booking);
		} catch (Exception e) {
			log.error(Booking.class, e);
			model.addAttribute("error", "Can not add booking.");
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteBooking" , method = RequestMethod.GET)
	public String goToDeleteBookingPage(Model model){
		String result = "deleteBooking";
		try {
			List<Booking> bookings = service.get();
			if(bookings.isEmpty()){
				model.addAttribute("error", "No data for delete");
				return "error";
			}
			model.addAttribute("bookings", bookings);
		} catch (Exception e) {
			log.error(Booking.class, e);
			model.addAttribute("error", "No data for open account");
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
	public String deleteBooking(@ModelAttribute("booking") int bookingId, Model model){
		String result = "index";
		try {
			service.delete(bookingId);
		} catch (Exception e) {
			log.error(Booking.class, e);
			model.addAttribute("error", "Can not delete booking");
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value="/getBooking", method = RequestMethod.POST)
	public ModelAndView getBooking(@ModelAttribute("bookingNumber") String bookingNumber, ModelAndView model){
		String result = "searchBooking";
		try {
			Booking booking = service.get(bookingNumber);
			if (booking == null){
				model.addObject("error", "Can not find booking with booking number:"+bookingNumber);
				model.setViewName("error");
				return model;
			}
			model.addObject("booking", booking);
		} catch (Exception e) {
			model.addObject("error", "Can not find booking with booking number:"+bookingNumber);
			result = "error";
		}
		model.setViewName(result);
		return model;
	}
	
	
}
