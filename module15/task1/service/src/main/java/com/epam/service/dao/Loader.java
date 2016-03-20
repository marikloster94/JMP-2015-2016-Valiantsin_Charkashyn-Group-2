package com.epam.service.dao;


public interface Loader {
	Object load(String filename, Class<?> className) throws Exception;
}
