package com.epam.mentoring.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.mentoring.exception.HsqlDBException;
import com.epam.mentoring.model.Currency;
import com.epam.mentoring.model.ExchangeRate;
import com.epam.mentoring.util.SQLUtil;

public class RateDAO implements IDAO {
	
	private final String INSERT = "INSERT INTO exchangeRate (id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo) VALUES (?,?,?,?,? )";
	private final String GET_ALL = "SELECT id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo from exchangeRate";
	private final String GET = "SELECT id, exchangeDay, rate, idCurrencyFrom, idCurrencyTo from exchangeRate where id = ?";
	private final String GET_ID = "SELECT max(id) from exchangeRate";
	
	
	@Override
	public void create(Object obj) throws HsqlDBException {
		ExchangeRate rate  = (ExchangeRate)obj;
		Connection conn = SQLUtil.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(INSERT);
			st.setInt(1, rate.getId());
			st.setString(2, rate.getExchangeDay());
			st.setDouble(3, rate.getRate().setScale(2, RoundingMode.HALF_UP).doubleValue());
			st.setInt(4,  rate.getFrom().getIdCurrency());
			st.setInt(5, rate.getTo().getIdCurrency());
			st.executeUpdate();
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add rate to db", ex);
		}
	}

	@Override
	public Object get(int id) throws SQLException {
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		ExchangeRate rate = null;
		try {
			PreparedStatement st = conn.prepareStatement(GET);
			st.setInt(1, id);
			rs = st.executeQuery(GET);
			while(rs.next()){
				int index = 1;
				rate = new ExchangeRate();
				rate.setId(rs.getInt(index++));
				rate.setExchangeDay(rs.getString(index++));
				rate.setRate(new BigDecimal(rs.getDouble(index++)));
				Currency from = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				rate.setFrom(from);
				Currency to = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				rate.setTo(to);
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot get rate from db", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return rate;
	}

	@Override
	public void update(Object obj) throws HsqlDBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws HsqlDBException {
		// TODO Auto-generated method stub

	}

	@Override
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
			throw new HsqlDBException("Cannot find last id of rates", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return index;
	}
	
	public List<ExchangeRate> getAll() throws SQLException{
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		List<ExchangeRate> rates = new ArrayList<ExchangeRate>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(GET_ALL);
			while(rs.next()){
				int index = 1;
				ExchangeRate rate = new ExchangeRate();
				rate.setId(rs.getInt(index++));
				rate.setExchangeDay(rs.getString(index++));
				rate.setRate(new BigDecimal(rs.getDouble(index++)));
				Currency from = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				rate.setFrom(from);
				Currency to = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				rate.setTo(to);
				rates.add(rate);
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot get rates from db", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return rates;
	}

}
