package com.epam.main;

import java.util.List;

import com.epam.facade.Facade;
import com.epam.model.Figure;

public class Runner {

	public static void main(String[] args) {
		List<Figure> figures = Facade.createFigures();
		for(Figure figure:figures){
			String square = String.format("%.2f", figure.calculateSquare());
			System.out.println("Square of "+figure.getName()+" is "+square);
		}
	}

}
