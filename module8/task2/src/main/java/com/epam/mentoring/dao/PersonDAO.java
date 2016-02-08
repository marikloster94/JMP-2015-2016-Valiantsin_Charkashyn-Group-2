package com.epam.mentoring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.mentoring.exception.HsqlDBException;
import com.epam.mentoring.model.Person;
import com.epam.mentoring.util.SQLUtil;

public class PersonDAO implements IDAO {

	private final String INSERT = "INSERT INTO person (id, name, surname, dateOfBirth, passportNumber) VALUES (?,?,?,?,? )";
	private final String GET_ALL = "SELECT id, name, surname, dateOfBirth, passportNumber from person";
	private final String GET = "SELECT id, name, surname, dateOfBirth, passportNumber from person where id = ?";
	private final String GET_BY_PASS = "SELECT id, name, surname, dateOfBirth, passportNumber from person where passportNumber = ?";
	private final String GET_ID = "SELECT max(id) from person";
	
	
	@Override
	public void create(Object obj) throws HsqlDBException {
		Person person  = (Person)obj;
		Connection conn = SQLUtil.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(INSERT);
			st.setInt(1, person.getId());
			st.setString(2, person.getName());
			st.setString(3, person.getSurname());
			st.setString(4, person.getDateOfBirth());
			st.setString(5, person.getPassportNumber());
			st.executeUpdate();
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add person todb", ex);
		}

	}
	
	public List<Person> getAll() throws SQLException {
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		List<Person> persons = new ArrayList<Person>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(GET_ALL);
			while(rs.next()){
				int index = 1;
				Person person = new Person();
				person.setId(rs.getInt(index++));
				person.setName(rs.getString(index++));
				person.setSurname(rs.getString(index++));
				person.setDateOfBirth(rs.getString(index++));
				person.setPassportNumber(rs.getString(index++));
				persons.add(person);
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add person todb", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return persons;

	}
	

	@Override
	public Object get(int id) throws SQLException {
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		Person person = null;
		try {
			PreparedStatement st = conn.prepareStatement(GET);
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()){
				int index = 1;
				person = new Person();
				person.setId(rs.getInt(index++));
				person.setName(rs.getString(index++));
				person.setSurname(rs.getString(index++));
				person.setDateOfBirth(rs.getString(index++));
				person.setPassportNumber(rs.getString(index++));
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add person todb", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return person;
	}
	
	public Object get(String passportNumber) throws SQLException {
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		Person person = null;
		try {
			PreparedStatement st = conn.prepareStatement(GET_BY_PASS);
			st.setString(1, passportNumber);
			rs = st.executeQuery();
			while(rs.next()){
				int index = 1;
				person = new Person();
				person.setId(rs.getInt(index++));
				person.setName(rs.getString(index++));
				person.setSurname(rs.getString(index++));
				person.setDateOfBirth(rs.getString(index++));
				person.setPassportNumber(rs.getString(index++));
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot get person by passportNumber", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return person;
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}
	
	public int getLastId() throws SQLException{
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		int index = 1;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(GET_ID);
			while(rs.next()){
				index = rs.getInt(1) + 1;
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot find last id of persons", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return index;
	}

}
