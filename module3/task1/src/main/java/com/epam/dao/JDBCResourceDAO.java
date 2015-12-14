package com.epam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.model.Person;

public class JDBCResourceDAO implements ResourceDAO {

	private static final String WRITE_OBJECT_SQL = "INSERT INTO java_objects(object_name, object_value) VALUES (?, ?)";

	private static final String READ_OBJECT_SQL = "SELECT object_value FROM java_objects WHERE object_id = ?";

	private static final String READ_ALL_OBJECT_SQL = "SELECT object_value FROM java_objects";

	@Override
	public void addPerson(Connection connection, Person person)
			throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String className = person.getClass().getName();
			pstmt = connection.prepareStatement(WRITE_OBJECT_SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, className);
			pstmt.setObject(2, person);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

	}

	@Override
	public byte[] getFirstPerson(Connection connection) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(READ_OBJECT_SQL);
			pstmt.setLong(1, 1);
			rs = pstmt.executeQuery();
			rs.next();
			byte[] st = (byte[]) rs.getObject(1);
			return st;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

	@Override
	public List<byte[]> getPersons(Connection connection) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<byte[]> list = new ArrayList<byte[]>();
		try {
			pstmt = connection.prepareStatement(READ_ALL_OBJECT_SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				byte[] st = (byte[]) rs.getObject(1);
				list.add(st);
			}
			return list;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

}
