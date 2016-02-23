package com.epam.dao;

import java.sql.SQLException;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

import com.epam.model.Person;


public class PersonDAOTest {
	
	@Test
	public void getPersonByPassNumber() throws SQLException{
		PersonDAO dao = EasyMock.createMockBuilder(PersonDAO.class).addMockedMethod("get", String.class).createMock();
		Person person = EasyMock.createMockBuilder(Person.class).createMock();
		EasyMock.expect(dao.get("MC3451654")).andReturn(person);
		EasyMock.replay(dao);
		EasyMock.replay(person);
		Assert.assertEquals(dao.get("MC3451654"), person);
	}
	
	@Test
	public void getNullPersonByPassNumber() throws SQLException{
		PersonDAO dao = EasyMock.createMockBuilder(PersonDAO.class).addMockedMethod("get", String.class).createMock();
		EasyMock.expect(dao.get("MC3451654")).andReturn(null);
		EasyMock.replay(dao);
		Assert.assertNull(dao.get("MC3451654"));
	}
	
	@Test
	public void getPersonById() throws SQLException{
		PersonDAO dao = EasyMock.createMockBuilder(PersonDAO.class).addMockedMethod("get", int.class).createMock();
		Person person = EasyMock.createMockBuilder(Person.class).createMock();
		EasyMock.expect(dao.get(1)).andReturn(person);
		EasyMock.replay(dao);
		EasyMock.replay(person);
		Assert.assertEquals(dao.get(1), person);
	}
	
	@Test
	public void getNullPersonById() throws SQLException{
		PersonDAO dao = EasyMock.createMockBuilder(PersonDAO.class).addMockedMethod("get", int.class).createMock();
		EasyMock.expect(dao.get(12345)).andReturn(null);
		EasyMock.replay(dao);
		Assert.assertNull(dao.get(12345));
	}
}
