package com.epam.model;

import java.util.List;

import javax.swing.JFrame;

import com.epam.bridge.PresentationAPI;

public class CarList extends AbstractList {

	private List<Car> cars;

	public CarList(PresentationAPI api) {
		super(api);
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public void presentateList(JFrame frame) {
		api.show(cars, frame);

	}

}
