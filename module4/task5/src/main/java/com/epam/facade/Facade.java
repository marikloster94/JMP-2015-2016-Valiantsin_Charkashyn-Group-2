package com.epam.facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Facade {
	public static Connection getConnection() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:file:dbpath/dbname", "SA", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void createTable(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE city (id IDENTITY , value VARCHAR(25))";
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertData(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			String query = "INSERT INTO city (value) VALUES('Moscow')";
			statement.executeUpdate(query);
			query = "INSERT INTO city (value) VALUES('Minsk')";
			statement.executeUpdate(query);
			query = "INSERT INTO city (value) VALUES('London')";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void readData(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT id, value FROM city";
			ResultSet resultSet = statement.executeQuery(query);
			statement.close();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
