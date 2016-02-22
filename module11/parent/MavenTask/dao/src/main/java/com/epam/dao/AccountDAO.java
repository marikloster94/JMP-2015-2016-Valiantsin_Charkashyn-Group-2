package com.epam.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.exception.HsqlDBException;
import com.epam.model.Account;
import com.epam.model.Currency;
import com.epam.model.Person;
import com.epam.util.SQLUtil;



public class AccountDAO implements IDAO {

	private final String INSERT = "INSERT INTO account (id, description, startDate, endDate, idPerson, accountValue, idCurrency) VALUES (?,?,?,?,?,?,?)";
	private final String GET_ALL = "SELECT id, description, startDate, endDate, idPerson, accountValue, idCurrency from account";
	private final String GET = "SELECT id, description, startDate, endDate, idPerson, accountValue, idCurrency from account where id = ?";
	private final String GET_ID = "SELECT max(id) from account";
	private final String UPDATE = "UPDATE account set idPerson = ?, accountValue = ?, idCurrency = ?  where id = ?";
	
	
	public void create(Object obj) throws HsqlDBException {
		Account acc = (Account) obj;
		Connection conn = SQLUtil.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(INSERT);
			st.setInt(1, acc.getId());
			st.setString(2, acc.getDescription());
			st.setString(3, acc.getStartdDate());
			st.setString(4, acc.getEndDate());
			st.setInt(5, acc.getPerson().getId());
			st.setDouble(6, acc.getValue().doubleValue());
			st.setInt(7, acc.getCurr().getIdCurrency());
			st.executeUpdate();
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add rate to db", ex);
		}
	}

	public Object get(int id) throws SQLException {
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		Account acc  = null; 
		try {
			PreparedStatement st = conn.prepareStatement(GET);
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()){
				int index = 1;
				acc = new Account();
				acc.setId(rs.getInt(index++));
				acc.setDescription(rs.getString(index++));
				acc.setStartdDate(rs.getString(index++));
				acc.setEndDate(rs.getString(index++));
				Person person = (Person) new PersonDAO().get(rs.getInt(index++));
				acc.setPerson(person);
				acc.setValue(new BigDecimal(rs.getDouble(index++)));
				Currency curr = (Currency) new CurrencyDAO().get(rs.getInt(index++));
				acc.setCurr(curr);
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
		return acc;
	}

	public void update(Object obj) throws SQLException {
		Account acc = (Account) obj;
		Connection conn = SQLUtil.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(UPDATE);
			st.setInt(1, acc.getPerson().getId());
			st.setDouble(2, acc.getValue().doubleValue());
			st.setInt(3, acc.getCurr().getIdCurrency());
			st.setInt(4, acc.getId());
			st.executeUpdate();
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add person todb", ex);
		}finally{
			if(conn != null)
				conn.close();
		}
	}

	public void delete(int id) throws HsqlDBException {
		// TODO Auto-generated method stub

	}

	public int getLastId() throws SQLException {
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
			throw new HsqlDBException("Cannot find last id of accounts", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return index;
	}
	
	public List<Account> getAll() throws SQLException{
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		List<Account> accs = new ArrayList<Account>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(GET_ALL);
			while(rs.next()){
				int index = 1;
				Account acc = new Account();
				acc.setId(rs.getInt(index++));
				acc.setDescription(rs.getString(index++));
				acc.setStartdDate(rs.getString(index++));
				acc.setEndDate(rs.getString(index++));
				Person person = (Person) new PersonDAO().get(rs.getInt(index++));
				acc.setPerson(person);
				acc.setValue(new BigDecimal(rs.getDouble(index++)));
				Currency curr = (Currency) new CurrencyDAO().get(rs.getInt(index++));
				acc.setCurr(curr);
				accs.add(acc);
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
		return accs;
	}

}
