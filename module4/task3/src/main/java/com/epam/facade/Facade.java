package com.epam.facade;

import java.util.ArrayList;
import java.util.List;

import com.epam.model.Circle;
import com.epam.model.Figure;
import com.epam.model.Rectangle;
import com.epam.model.Square;

public class Facade {

	public static List<Figure> createFigures(){
		List<Figure> figures = new ArrayList<Figure>();
		Circle circle = new Circle("circle", 4.5);
		Rectangle rectangle = new Rectangle("rectangle", 4.5, 3.5);
		Square square = new Square("square", 4);
		figures.add(circle);
		figures.add(rectangle);
		figures.add(square);
		return figures;
	}
}
