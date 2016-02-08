package com.epam.mentoring.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.mentoring.exception.HsqlDBException;

public class SQLUtil {

	private static final Logger log = Logger.getLogger(SQLUtil.class);
	
	public static Connection getConnection(){
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			log.error(SQLUtil.class, e);
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:file:dbpath/dbname", "SA", "");
		} catch (SQLException e) {
			log.error(SQLUtil.class, e);
		}
		return connection;
	}
	public static void createDb() throws Exception{
		
		try {
			Connection conn = getConnection();
			createTablePerson(conn);
			createTableCurrency(conn);
			createTableAccount(conn);
			createTableRate(conn);
			createTableTicket(conn);
			alterTableAccount(conn);
			alterTableRate(conn);
			alterTableTicket(conn);
			insertCurrencyData(conn);
			insertPersonData(conn);
			insertAccountData(conn);
			insertRateData(conn);
			insertTicketData(conn);
		} catch(HsqlDBException ex){
			log.error(SQLUtil.class, ex);
		}
		
	}
	
	public static void createTablePerson(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE person (id int IDENTITY, name varchar(50) NOT NULL, surname varchar(50) NOT NULL, dateOfBirth varchar(50) NOT NULL, passportNumber varchar(50) NOT NULL )";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't create table person", e);
		}

	}
	public static void createTableCurrency(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE currency ( idCurrency int IDENTITY, shortName varchar(50) NOT NULL)";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't create table currency", e);
		}

	}
	public static void createTableAccount(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE account (id int IDENTITY, description varchar(225) NOT NULL, startDate varchar(50) NOT NULL, endDate varchar(50) NOT NULL, idPerson int NOT NULL, accountValue double NOT NULL, idCurrency int NOT NULL)";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't create table account", e);
		}

	}
	
	public static void createTableRate(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE exchangeRate ( id int IDENTITY, exchangeDay varchar(50) NOT NULL, idCurrencyFrom int NOT NULL, idCurrencyTo int NOT NULL, rate double NOT NULL)";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't create table exchangeRate", e);
		}

	}
	
	public static void createTableTicket(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE exchangeTicket ( id int IDENTITY, idPerson int NOT NULL, idCurrencyFrom int NOT NULL, idCurrencyTo int NOT NULL, status varchar(50) NOT NULL, toCurrAmount double NOT NULL)";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't create table exchangeTicket", e);
		}

	}
	public static void alterTableTicket(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query1 = "ALTER TABLE exchangeTicket ADD FOREIGN KEY (idCurrencyFrom) REFERENCES currency (idCurrency)";
			String query2 = "ALTER TABLE exchangeTicket ADD FOREIGN KEY (idCurrencyTo) REFERENCES currency (idCurrency)";
			String query3 = "ALTER TABLE exchangeTicket ADD FOREIGN KEY (idPerson) REFERENCES person (id)";
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			statement.executeUpdate(query3);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't add foreign key to table exchangeTicket", e);
		}

	}
	
	public static void alterTableRate(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query1 = "ALTER TABLE exchangeRate ADD FOREIGN KEY (idCurrencyFrom) REFERENCES currency (idCurrency)";
			String query2 = "ALTER TABLE exchangeRate ADD FOREIGN KEY (idCurrencyTo) REFERENCES currency (idCurrency)";
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			statement.close();
		}  catch (SQLException e) {
			throw new HsqlDBException("Cann't add foreign key to table exchangeRate", e);
		}

	}
	public static void alterTableAccount(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query1 = "ALTER TABLE account ADD FOREIGN KEY (idPerson) REFERENCES person (id)";
			String query2 = "ALTER TABLE account ADD FOREIGN KEY (idCurrency) REFERENCES currency (idCurrency)";
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);		
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't add foreign key to table account", e);
		}

	}
	
	public static void insertCurrencyData(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "INSERT INTO currency (idCurrency, shortName) VALUES (1, 'USD')";
			statement.executeUpdate(query);
			query = "INSERT INTO currency (idCurrency, shortName) VALUES (2, 'EUR')";
			statement.executeUpdate(query);
			query = "INSERT INTO currency (idCurrency, shortName) VALUES (3, 'BYR')";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't insert data in table currency", e);
		}
	}

	public static void insertPersonData(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "INSERT INTO person (id, name, surname, dateOfBirth, passportNumber) VALUES (1, 'Ivan', 'Petrov', '12/02/1980', 'MC2341234' )";
			statement.executeUpdate(query);
			query = "INSERT INTO person (id, name, surname, dateOfBirth, passportNumber) VALUES (2, 'Oksana', 'Voronko', '19/11/1968', 'MC7798651' )";
			statement.executeUpdate(query);
			query = "INSERT INTO person (id, name, surname, dateOfBirth, passportNumber) VALUES (3, 'NIkolai', 'Egenkoy', '12/02/1976', 'MC2345674' )";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't insert data in table person", e);
		}
	}
	
	public static void insertAccountData(Connection connection) throws HsqlDBException {
		try {
			Statement statement = connection.createStatement();
			String query = "INSERT INTO account (id, description, startDate, endDate, idPerson, accountValue, idCurrency) VALUES (1, 'Deposit USD', '12/02/2015', '12/02/2016', 1, 3450, 1)";
			statement.executeUpdate(query);
			query = "INSERT INTO account (id, description, startDate, endDate, idPerson, accountValue, idCurrency) VALUES (2, 'Deposit EUR', '12/01/2016', '12/10/2016', 2, 5500, 2)";
			statement.executeUpdate(query);
			query = "INSERT INTO account (id, description, startDate, endDate, idPerson, accountValue, idCurrency) VALUES (3, 'Deposit EUR (bank)', '12/01/2016', '12/10/2020', 3, 10000, 2)";
			statement.executeUpdate(query);
			query = "INSERT INTO account (id, description, startDate, endDate, idPerson, accountValue, idCurrency) VALUES (4, 'Deposit USD (bank)', '12/01/2016', '12/10/2020', 3, 15000, 1)";
			statement.executeUpdate(query);
			query = "INSERT INTO account (id, description, startDate, endDate, idPerson, accountValue, idCurrency) VALUES (5, 'Deposit BYR (bank)', '12/01/2016', '12/10/2020', 3, 550000000000, 3)";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't insert data in table account", e);
		}
	}
	
	public static void insertRateData(Connection connection) throws SQLException {
		Statement statement =null;
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (1, '08/02/2016', 4.42E-5, 3, 1)";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (2, '09/02/2016', 4.40E-5, 3, 1)";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (3, '08/02/2016', 4.08E-5, 3, 2)";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (4, '09/02/2016', 4.10E-5, 3, 2)";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (5, '08/02/2016', 0.8921, 1, 2)";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (6, '08/02/2016', 1.109, 2, 1)";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (7, '09/02/2016', 0.9, 1, 2)";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (8, '09/02/2016', 1.106, 2, 1)";
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't insert data in table account", e);
		}finally{
			if(statement != null){
				statement.close();
			}
		}
	}
	
	public static void insertTicketData(Connection connection) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO exchangeTicket (id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status) VALUES (1, 200, 1, 1, 2, 'new')";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeTicket (id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status) VALUES (2, 230, 1, 1, 2, 'new')";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeTicket (id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status) VALUES (3, 100, 1, 1, 2, 'new')";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeTicket (id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status) VALUES (4, 200, 1, 1, 2, 'new')";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeTicket (id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status) VALUES (5, 400, 2, 2, 1, 'new')";
			statement.executeUpdate(query);
			query = "INSERT INTO exchangeTicket (id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status) VALUES (6, 150, 2, 2, 1, 'new')";
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			throw new HsqlDBException("Cann't insert data in table account", e);
		}finally{
			if(statement != null){
				statement.close();
			}
		}
	}

}
