package com.epam.main;

import java.sql.Connection;

import com.epam.facade.Facade;

public class Runner {

	public static void main(String[] args) {
		System.out.println("Get connection to db");
		Connection connection = Facade.getConnection();
		System.out.println("Create table in db");
		Facade.createTable(connection);
		System.out.println("Insert data to table");
		Facade.insertData(connection);
		System.out.println("Read data from table");
		Facade.readData(connection);
		System.out.println("Close connection to db");
		Facade.closeConnection(connection);

	}

}
