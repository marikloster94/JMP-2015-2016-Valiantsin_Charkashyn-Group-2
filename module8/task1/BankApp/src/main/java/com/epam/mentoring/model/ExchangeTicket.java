package com.epam.mentoring.model;

import java.io.Serializable;

public class ExchangeTicket implements Serializable {


	private static final long serialVersionUID = -2480266429345567122L;

	private Person client;
	private Currency fromCurr;
	private Currency toCurr;
	private double fromCurrAmount;

	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}

	public Currency getFromCurr() {
		return fromCurr;
	}

	public void setFromCurr(Currency fromCurr) {
		this.fromCurr = fromCurr;
	}

	public Currency getToCurr() {
		return toCurr;
	}

	public void setToCurr(Currency toCurr) {
		this.toCurr = toCurr;
	}

	public double getFromCurrAmount() {
		return fromCurrAmount;
	}

	public void setFromCurrAmount(double fromCurrAmount) {
		this.fromCurrAmount = fromCurrAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result
				+ ((fromCurr == null) ? 0 : fromCurr.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fromCurrAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(fromCurrAmount) != Double
				.doubleToLongBits(other.fromCurrAmount))
			return false;
		if (toCurr == null) {
			if (other.toCurr != null)
				return false;
		} else if (!toCurr.equals(other.toCurr))
			return false;
		return true;
	}

}
