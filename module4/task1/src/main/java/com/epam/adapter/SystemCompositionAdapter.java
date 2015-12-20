package com.epam.adapter;

import java.io.PrintStream;
import java.util.List;

import com.epam.model.Car;

public class SystemCompositionAdapter {

	private PrintStream out;

	public SystemCompositionAdapter() {
		this.out = new PrintStream(System.out);
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void printList(List list) {
		out.println("Composition adapter:");
		if (list.get(0) instanceof Car) {
			out.format("%8s%16s%10s", "Car type", "Mark", "Age");
			out.println();
			for (Car obj : (List<Car>) list) {
				out.format("%8s%16s%10d", obj.getCarType(), obj.getMark(),
						obj.getAge());
				out.println();
			}
		} else {
			out.println(list);
		}

	}
}
