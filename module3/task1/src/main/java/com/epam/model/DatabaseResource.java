package com.epam.model;

import java.sql.Connection;
import java.sql.SQLException;



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
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
