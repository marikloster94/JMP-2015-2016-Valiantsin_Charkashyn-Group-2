package com.epam.adapter;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import com.epam.model.Car;

public class SystemInheritanceAdapter extends PrintStream {

	public SystemInheritanceAdapter(OutputStream out) {
		super(out);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void println(Object object) {
		super.println("Inheritance adapter:");
		if (object instanceof List) {
			if (((List) object).get(0) instanceof Car) {
				super.format("%8s%16s%10s", "Car type", "Mark", "Age");
				super.println();
				for (Car obj : (List<Car>) object) {
					super.format("%8s%16s%10d", obj.getCarType(),
							obj.getMark(), obj.getAge());
					super.println();
				}
			} else {
				super.println(object);
			}
		} else {
			super.println(object);
		}
	}

}
