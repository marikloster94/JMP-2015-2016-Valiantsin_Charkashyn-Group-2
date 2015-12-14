package com.epam.model;

import java.sql.Connection;



public class DatabaseResource extends Resource {

	private Connection connection;
	
	public DatabaseResource() {

	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
