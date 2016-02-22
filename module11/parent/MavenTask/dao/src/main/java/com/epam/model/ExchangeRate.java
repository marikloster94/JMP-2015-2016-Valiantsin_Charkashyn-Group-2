package com.epam.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExchangeRate implements Serializable {

	private static final long serialVersionUID = -8251790741274076159L;
	private int id;
	private Currency from;
	private Currency to;
	private BigDecimal rate;
	private String exchangeDay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getExchangeDay() {
		return exchangeDay;
	}

	public void setExchangeDay(String exchangeDay) {
		this.exchangeDay = exchangeDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exchangeDay == null) ? 0 : exchangeDay.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + id;
		long temp;
		temp = rate.longValue();
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		ExchangeRate other = (ExchangeRate) obj;
		if (exchangeDay == null) {
			if (other.exchangeDay != null)
				return false;
		} else if (!exchangeDay.equals(other.exchangeDay))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (id != other.id)
			return false;
		if (rate.longValue() != other.rate.longValue())
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

}
