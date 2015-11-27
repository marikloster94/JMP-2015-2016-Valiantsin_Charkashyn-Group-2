package com.epam.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Labyrinth {

	private List<String> square;
	private HashMap<String, Integer[]> entrance; 
	
	public Labyrinth() {
		this.square = new ArrayList<String>();
	}

	public List<String> getSquare() {
		return square;
	}

	public void setSquare(List<String> square) {
		this.square = square;
	}

	public HashMap<String, Integer[]> getEntrance() {
		return entrance;
	}

	public void setEntrance(HashMap<String, Integer[]> entrance) {
		this.entrance = entrance;
	}

}
