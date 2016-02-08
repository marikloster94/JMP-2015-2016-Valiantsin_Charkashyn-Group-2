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
import com.epam.mentoring.model.ExchangeTicket;
import com.epam.mentoring.model.Person;
import com.epam.mentoring.util.SQLUtil;

public class ExchangeTicketDAO implements IDAO {

	private final String INSERT = "INSERT INTO exchangeTicket (id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status) VALUES (?,?,?,?,?,?)";
	private final String GET_ALL = "SELECT id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status from exchangeTicket";
	private final String GET_ALL_WITH_STATUS = "SELECT id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status from exchangeTicket where status = ?";
	private final String GET = "SELECT id, toCurrAmount, idPerson, idCurrencyFrom, idCurrencyTo, status from exchangeTicket where id = ?";
	private final String GET_ID = "SELECT max(id) from exchangeTicket";
	private final String UPDATE = "UPDATE exchangeTicket set status = ? where id = ?";
	
	@Override
	public void create(Object obj) throws HsqlDBException {
		ExchangeTicket ticket  = (ExchangeTicket)obj;
		Connection conn = SQLUtil.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(INSERT);
			st.setInt(1, ticket.getId());
			st.setInt(5, ticket.getToCurr().getIdCurrency());
			st.setString(6, ticket.getStatus());
			st.setInt(3, ticket.getClient().getId());
			st.setDouble(2, ticket.getToCurrAmount().setScale(2, RoundingMode.HALF_UP).doubleValue());
			st.setInt(4, ticket.getFromCurr().getIdCurrency());
			st.executeUpdate();
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot add ticket todb", ex);
		}

	}

	@Override
	public Object get(int id) throws SQLException {
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		ExchangeTicket ticket = null;
		try {
			PreparedStatement st = conn.prepareStatement(GET);
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()){
				int index = 1;
				ticket = new ExchangeTicket();
				ticket.setId(rs.getInt(index++));
				ticket.setToCurrAmount(new BigDecimal(rs.getDouble(index++)));
				Person person = (Person) new PersonDAO().get(rs.getInt(index++));
				ticket.setClient(person);
				Currency from = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				ticket.setFromCurr(from);
				Currency to = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				ticket.setToCurr(to);
				ticket.setStatus(rs.getString(index++));
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot get ticket from db", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return ticket;
	}

	@Override
	public void update(Object obj) throws SQLException {
		ExchangeTicket ticket = (ExchangeTicket) obj;
		Connection conn = SQLUtil.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(UPDATE);
			st.setString(1, ticket.getStatus());
			st.setInt(2, ticket.getId());
			st.executeUpdate();
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot update ticket", ex);
		}finally{
			if(conn != null)
				conn.close();
		}
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
			throw new HsqlDBException("Cannot find last id of exchangeTickets", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return index;
	}
	
	public List<ExchangeTicket> getAll() throws SQLException{
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		List<ExchangeTicket> tickets = new ArrayList<ExchangeTicket>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(GET_ALL);
			while(rs.next()){
				int index = 1;
				ExchangeTicket ticket = new ExchangeTicket();
				ticket.setId(rs.getInt(index++));
				ticket.setToCurrAmount(new BigDecimal(rs.getDouble(index++)));
				Person person = (Person) new PersonDAO().get(rs.getInt(index++));
				ticket.setClient(person);
				Currency from = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				ticket.setFromCurr(from);
				Currency to = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				ticket.setToCurr(to);
				ticket.setStatus(rs.getString(index++));
				tickets.add(ticket);
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot gel all tickets", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return tickets;
	}

	public List<ExchangeTicket> getAll(String status) throws SQLException{
		Connection conn = SQLUtil.getConnection();
		ResultSet rs = null;
		List<ExchangeTicket> tickets = new ArrayList<ExchangeTicket>();
		try {
			PreparedStatement st = conn.prepareStatement(GET_ALL_WITH_STATUS);
			st.setString(1, status);
			rs = st.executeQuery();
			while(rs.next()){
				int index = 1;
				ExchangeTicket ticket = new ExchangeTicket();
				ticket.setId(rs.getInt(index++));
				ticket.setToCurrAmount(new BigDecimal(rs.getDouble(index++)));
				Person person = (Person) new PersonDAO().get(rs.getInt(index++));
				ticket.setClient(person);
				Currency from = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				ticket.setFromCurr(from);
				Currency to = (Currency)new CurrencyDAO().get(rs.getInt(index++));
				ticket.setToCurr(to);
				ticket.setStatus(rs.getString(index++));
				tickets.add(ticket);
			}
			st.close();
		} catch (SQLException ex) {
			throw new HsqlDBException("Cannot get tickets", ex);
		}finally{
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
		}
		return tickets;
	}
}
