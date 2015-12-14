package com.epam.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.facade.ResourceFacade;
import com.epam.model.DatabaseResource;
import com.epam.model.Resource;

public class DatabaseFactory extends ResourceFactory {

	public DatabaseFactory() {
		resource = new DatabaseResource();
	}

	@Override
	public Resource createResource() {
		String driver = ResourceFacade.readFromProperty("database.driver_class");
		String url = ResourceFacade.readFromProperty("database.url");
		String userName = ResourceFacade.readFromProperty("database.username");
		String password = ResourceFacade.readFromProperty("database.password");
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
		return (DatabaseResource) resource;
	}

}
