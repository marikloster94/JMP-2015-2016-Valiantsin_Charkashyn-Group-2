package com.epam.mentoring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.mentoring.exception.HsqlDBException;
import com.epam.mentoring.model.Currency;
import com.epam.mentoring.util.SQLUtil;

public class CurrencyDAO implements IDAO {
	private final String INSERT = "INSERT INTO currency (idCurrency, shortName) VALUES (?,?)";
	private final String GET_ALL = "SELECT idCurrency, shortName from currency";
	private final String GET = "SELECT idCurrency, shortName from currency where idCurrency = ?";
	private final String GET_BY_NAME = "SELECT idCurrency, shortName from currency where shortName = ?";
	private final String GET_ID = "SELECT max(idCurrency) from currency";
	
	@Override
	public void create(Object obj) throws HsqlDBException {
		Currency curr  = (Currency)obj;
		Connection conn = SQLUtil.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(INSERT);
			st.setInt(1, curr.getIdCurrency());
			st.setString(2, curr.getShortName());
			st.executeUpdate();
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add currency to db", ex);
		}
	}

	@Override
	public Object get(int id) throws SQLException {
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		Currency currency = null; 
		try {
			PreparedStatement st = conn.prepareStatement(GET);
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()){
				int index = 1;
				currency = new Currency();
				currency.setIdCurrency(rs.getInt(index++));
				currency.setShortName(rs.getString(index++));
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot load currency from db", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return currency;
	}

	@Override
	public void update(Object obj) throws HsqlDBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws HsqlDBException {
		// TODO Auto-generated method stub

	}
	
	public List<Currency> getAll() throws SQLException{
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		List<Currency> currs = new ArrayList<Currency>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(GET_ALL);
			while(rs.next()){
				int index = 1;
				Currency currency = new Currency();
				currency.setIdCurrency(rs.getInt(index++));
				currency.setShortName(rs.getString(index++));
				
				currs.add(currency);
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot load currencies from db", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return currs;
	}
	
	public Currency get(String currencyName) throws SQLException{
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		Currency currency = null; 
		try {
			PreparedStatement st = conn.prepareStatement(GET_BY_NAME);
			st.setString(1, currencyName);
			rs = st.executeQuery();
			while(rs.next()){
				int index = 1;
				currency = new Currency();
				currency.setIdCurrency(rs.getInt(index++));
				currency.setShortName(rs.getString(index++));
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot load currencies from db", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return currency;
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
			throw new HsqlDBException("Cannot find last id of currencies", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return index;
	}

}
