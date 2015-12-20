package com.epam.main;

import java.util.ArrayList;
import java.util.List;

import com.epam.adapter.SystemCompositionAdapter;
import com.epam.adapter.SystemInheritanceAdapter;
import com.epam.model.Car;

public class Runner {

	public static void main(String [] args){
		List<Car> list = createList();
		System.out.println("Print list by inheritance adapter");
		SystemInheritanceAdapter inheritance  = new SystemInheritanceAdapter(System.out);
		inheritance.println(list);
		System.out.println("Print list by compositon adapter");
		SystemCompositionAdapter adapter  = new SystemCompositionAdapter();
		adapter.printList(list);
		inheritance.close();
		adapter.getOut().close();
		
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
