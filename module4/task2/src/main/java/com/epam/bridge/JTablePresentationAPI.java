package com.epam.bridge;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.epam.model.Car;

public class JTablePresentationAPI implements PresentationAPI {

	@SuppressWarnings("rawtypes")
	public void show(List list, JFrame frame) {
		String[] columnNames = { "Type", "Mark", "Age" };
		
		String[][] data = new String[list.size()][3];
		
		for(int i =0;i<list.size();i++){
			Car car = (Car) list.get(i);
			data[i][0] = car.getCarType();
			data[i][1] = car.getMark();
			data[i][2] = String.valueOf(car.getAge());
		}

		JTable table = new JTable(data, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);

		frame.getContentPane().add(scrollPane);
	}
}
