package com.epam.service;

import com.epam.util.SQLUtil;

public class DatabaseService {

	public void createDB() throws Exception{
		SQLUtil.createDb();
	}
}
