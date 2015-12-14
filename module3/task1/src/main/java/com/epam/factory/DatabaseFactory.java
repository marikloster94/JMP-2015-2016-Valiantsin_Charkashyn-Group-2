package com.epam.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.model.DatabaseResource;
import com.epam.model.Resource;
import com.epam.property.PropertyReader;

public class DatabaseFactory extends ResourceFactory {

	public DatabaseFactory() {
		resource = new DatabaseResource();
	}

	@Override
	public Resource createResource() {
		String driver = PropertyReader.readFromProperty("database.driver_class");
		String url = PropertyReader.readFromProperty("database.url");
		String userName = PropertyReader.readFromProperty("database.username");
		String password = PropertyReader.readFromProperty("database.password");
		if (driver != null && url != null && userName != null && password != null) {
			try {
				Class.forName(driver).newInstance();
				Connection conn = DriverManager.getConnection(url, userName, password);
				resource.serResourceName(url);
				((DatabaseResource) resource).setConnection(conn);
			} catch (InstantiationException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return resource;
	}

}
