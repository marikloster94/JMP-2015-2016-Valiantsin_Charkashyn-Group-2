package com.epam.service.dao;


public interface Writer {
	void write(String filename, Object data, Class<?> className) throws Exception;
}
