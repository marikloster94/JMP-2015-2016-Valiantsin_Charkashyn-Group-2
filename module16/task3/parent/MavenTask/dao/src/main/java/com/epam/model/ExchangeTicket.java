package com.epam.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exchangeTicket")
public class ExchangeTicket {

	private int idTicket;
	private Person client = new Person();
	private String fromCurr;
	private String toCurr;
	private double toCurrAmount;
	private String status;
	
	@Id
	@Column(name="ticketId")
	@GeneratedValue
	public int getId() {
		return idTicket;
	}

	public void setId(int id) {
		this.idTicket = id;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idPerson", nullable = false)
	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}
	@Column(name="fromCurr")
	public String getFromCurr() {
		return fromCurr;
	}

	public void setFromCurr(String fromCurr) {
		this.fromCurr = fromCurr;
	}
	@Column(name="toCurr")
	public String getToCurr() {
		return toCurr;
	}

	public void setToCurr(String toCurr) {
		this.toCurr = toCurr;
	}
	@Column(name="toCurrAmount")
	public double getToCurrAmount() {
		return toCurrAmount;
	}

	public void setToCurrAmount(double toCurrAmount) {
		this.toCurrAmount = toCurrAmount;
	}
	@Column(name="exchangeStatus")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
