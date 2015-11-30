package com.epam.game.model;

import java.util.HashMap;

public class Labyrinth{

	private String [][] square;
	private HashMap<String, Integer[]> entrance; 
	private Integer[] exit; 
	
	public Labyrinth() {
		
	}

	public Integer[] getExit() {
		return exit;
	}

	public void setExit(Integer[] exit) {
		this.exit = exit;
	}

	public String[][] getSquare() {
		return square;
	}


	public void setSquare(String[][] square) {
		this.square = square;
	}


	public HashMap<String, Integer[]> getEntrance() {
		return entrance;
	}

	public void setEntrance(HashMap<String, Integer[]> entrance) {
		this.entrance = entrance;
	}

}
