package com.epam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.dao.BookingDAO;
import com.epam.model.Booking;

@Repository
public class BookingService {

	@Autowired
	BookingDAO dao;
	
	private static final Logger log = Logger.getLogger(BookingService.class);
	
	@Transactional
	public Booking add(Booking booking){
		return null;
	}
	
	@Transactional
	public void delete(int id) throws Exception{
		Booking booking = null;
		try {
			booking = get(id);
		} catch (Exception e) {
			log.error(BookingService.class, e);
		}
		if (booking == null) {
			throw new Exception("Can not delete non-existing booking");
		}
		dao.delete(booking);
	}
	
	public Booking get(String bookingNumber) throws Exception{
		if(bookingNumber == null ||bookingNumber.isEmpty()){
			throw new Exception("Empty bookingNumber");
		}
		return dao.get(bookingNumber);
	}
	
	public Booking get(int id) throws Exception{
		return dao.get(id);
	}
	
	public List<Booking> get() throws Exception{
		return dao.getBookings(BookingDAO.GET_ALL);
	}
	
	public List<Booking> getByQuery(String query) throws Exception{
		return dao.getBookings(query);
	}
}
