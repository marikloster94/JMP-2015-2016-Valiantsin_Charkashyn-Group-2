package com.epam.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="booking")
@Access(AccessType.PROPERTY)
public class Booking {

	private int idBooking;
	private String bookingNumber;
	private Date bookingDate;
	private int place;
	private double price;
	private Person client;

	@Id
	@Column(name="idBooking")
	@GeneratedValue
	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}
	
	@Column(name="number")
	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	
	@Column(name="bookingDate")
	@Temporal(TemporalType.TIMESTAMP) 
	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	@Column(name="place")
	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
	@Column(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idPerson", nullable = false)
	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}

}
