package com.epam.model;

import java.io.Serializable;

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
public class ExchangeTicket implements Serializable {


	private static final long serialVersionUID = -2480266429345567122L;

	private int id;
	private Person client;
	private String fromCurr;
	private String toCurr;
	private double toCurrAmount;
	private String status;
	
	@Id
	@Column(name="ticketId")
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result
				+ ((fromCurr == null) ? 0 : fromCurr.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toCurr == null) ? 0 : toCurr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExchangeTicket other = (ExchangeTicket) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (fromCurr == null) {
			if (other.fromCurr != null)
				return false;
		} else if (!fromCurr.equals(other.fromCurr))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (toCurr == null) {
			if (other.toCurr != null)
				return false;
		} else if (!toCurr.equals(other.toCurr))
			return false;
		if (toCurrAmount!= other.toCurrAmount)
			return false;
		return true;
	}

}
