package com.epam.model;

import java.util.Date;
import java.util.List;

import com.epam.model.ajax.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class ShowTime {

	@JsonView(Views.Public.class)
	private String date;
	@JsonView(Views.Public.class)
	private List<Integer> places;
	@JsonView(Views.Public.class)
	private String movie;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Integer> getPlaces() {
		return places;
	}

	public void setPlaces(List<Integer> places) {
		this.places = places;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}
}
