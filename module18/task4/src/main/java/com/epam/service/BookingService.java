package com.epam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.dao.BookingDAO;
import com.epam.model.Booking;

@Repository
public class BookingService {

	@Autowired
	BookingDAO dao;
	
	@Transactional
	public Booking add(Booking booking){
		return null;
	}
	
	@Transactional
	public void delete(int id){
		
	}
	
	public Booking get(String bookingNumber){
		return null;
	}
	
	public Booking get(int id){
		return null;
	}
	
	public List<Booking> get(){
		return null;
	}
	
	public List<Booking> getByQuery(String query){
		return null;
	}
}
