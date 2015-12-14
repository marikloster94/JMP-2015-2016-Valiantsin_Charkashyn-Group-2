package com.epam.factory.method;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.dao.JDBCResourceDAO;
import com.epam.dao.ResourceDAO;
import com.epam.model.DatabaseResource;
import com.epam.model.Person;

public class DatabaseFactoryMethod extends ResourceFactoryMethod {

	private final ResourceDAO dao = new JDBCResourceDAO();

	public DatabaseFactoryMethod() {
		resource = new DatabaseResource();
	}

	@Override
	public void writePerson(Person person) {
		Connection connection = ((DatabaseResource) resource).getConnection();
		try {
			dao.addPerson(connection, person);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Person readPerson() {
		Connection connection = ((DatabaseResource) resource).getConnection();
		Person person = null;
		try {
			byte[] st = dao.getFirstPerson(connection);
			ByteArrayInputStream baip = new ByteArrayInputStream(st);
			ObjectInputStream ois = new ObjectInputStream(baip);
			person = (Person) ois.readObject();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return person;

	}

	@Override
	public Person readPerson(String name) {
		Connection connection = ((DatabaseResource) resource).getConnection();
		Person person = null;
		try {
			List<byte[]> list = dao.getPersons(connection);
			for(byte[] st: list ) {
				ByteArrayInputStream baip = new ByteArrayInputStream(st);
				ObjectInputStream ois = new ObjectInputStream(baip);
				person = (Person) ois.readObject();
				if(name.equals(person.getName())){
					return person;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
