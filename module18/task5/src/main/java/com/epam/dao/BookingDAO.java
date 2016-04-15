package com.epam.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.model.Booking;
import com.epam.util.DateUtil;

@Component
public class BookingDAO {

	@PersistenceContext
	private EntityManager em;
	
	public static final String GET_BOOKING_BY_BOKKINGNUMBER = "SELECT b FROM Booking b WHERE b.bookingNumber=:bookingNumber";
	public static final String GET_BOOKINGS_ON_DATE = "SELECT b FROM Booking b WHERE b.bookingDate>=:startBookingDate and b.bookingDate<:endBookingDate";
	public static final String GET_ALL = "SELECT b FROM Booking b";
	
	public Booking add(Booking booking) throws Exception {
		if (booking == null) {
			throw new IllegalArgumentException("Booking can not be null");
		}
		return em.merge(booking);
	}
	
	public void delete(Booking booking) throws Exception {
		em.remove(booking);
	}
	
	public Booking get(String bookingNumber) throws Exception {
		return (Booking)em.createQuery(GET_BOOKING_BY_BOKKINGNUMBER).setParameter("bookingNumber", bookingNumber).getSingleResult();
	}
	
	public Booking get(int id) throws Exception {
		return em.find(Booking.class, id);
	}
	
	public List<Booking> getBookings(Date date) throws Exception {
		return em.createQuery(GET_BOOKINGS_ON_DATE).setParameter("startBookingDate", date).setParameter("endBookingDate", DateUtil.addDays(date, 1)).getResultList();
	}
	
	public List<Booking> getBookings(String query) throws Exception {
		return em.createQuery(query).getResultList();
	}
	
}
