package com.epam.factory.method;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.model.DatabaseResource;
import com.epam.model.Person;

public class DatabaseFactoryMethod extends ResourceFactoryMethod {

	static final String WRITE_OBJECT_SQL = "INSERT INTO java_objects(object_name, object_value) VALUES (?, ?)";

	static final String READ_OBJECT_SQL = "SELECT object_value FROM java_objects WHERE object_id = ?";

	static final String READ_ALL_OBJECT_SQL = "SELECT object_value FROM java_objects";

	public DatabaseFactoryMethod() {
		resource = new DatabaseResource();
	}

	@Override
	public void writePerson(Person person) {
		Connection connection = ((DatabaseResource) resource).getConnection();
		try {
			String className = person.getClass().getName();
			PreparedStatement pstmt = connection.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, className);
			pstmt.setObject(2, person);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public Person readPerson() {
		Connection connection = ((DatabaseResource) resource).getConnection();
		PreparedStatement pstmt;
		Person person = null;
		try {
			pstmt = connection.prepareStatement(READ_OBJECT_SQL);
			pstmt.setLong(1, 1);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			byte[] st = (byte[]) rs.getObject(1);
			ByteArrayInputStream baip = new ByteArrayInputStream(st);
			ObjectInputStream ois = new ObjectInputStream(baip);
			person = (Person) ois.readObject();
			rs.close();
			pstmt.close();
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
		PreparedStatement pstmt;
		Person person = null;
		try {
			pstmt = connection.prepareStatement(READ_ALL_OBJECT_SQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				byte[] st = (byte[]) rs.getObject(1);
				ByteArrayInputStream baip = new ByteArrayInputStream(st);
				ObjectInputStream ois = new ObjectInputStream(baip);
				person = (Person) ois.readObject();
				if(person.getName().equals(name)){
					return person;
				}
			}
			rs.close();
			pstmt.close();
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
