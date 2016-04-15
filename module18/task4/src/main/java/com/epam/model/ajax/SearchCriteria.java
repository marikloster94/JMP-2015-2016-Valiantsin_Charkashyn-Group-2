package com.epam.model.ajax;

import com.fasterxml.jackson.annotation.JsonView;

public class SearchCriteria {

	@JsonView(Views.Public.class)
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
