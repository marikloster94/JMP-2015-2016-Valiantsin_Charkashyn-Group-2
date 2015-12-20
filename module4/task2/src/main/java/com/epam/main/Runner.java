package com.epam.main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.epam.bridge.JListPresentationAPI;
import com.epam.bridge.JTablePresentationAPI;
import com.epam.model.Car;
import com.epam.model.CarList;

public class Runner {

	public static void main(String[] args) { 
		
		JFrame frame = new JFrame("Bridge pattern example");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    List<Car> cars = createList();
	    CarList jlist = new CarList(new JListPresentationAPI());
	    jlist.setCars(cars);
	    jlist.presentateList(frame);
	    frame.setSize(400, 400);
	    CarList jtable = new CarList(new JTablePresentationAPI());
	    jtable.setCars(cars);
	    jtable.presentateList(frame);
	    frame.setVisible(true);
	    
	    
	}
	
	public static List<Car> createList(){
		List<Car> cars = new ArrayList<Car>();
		Car mazda = new Car();
		mazda.setCarType("sedan");
		mazda.setMark("MAZDA");
		mazda.setAge(2);
		Car ford = new Car();
		ford.setCarType("sedan");
		ford.setMark("FORD");
		ford.setAge(4);
		cars.add(ford);
		cars.add(mazda);
		return cars;
	}

}
