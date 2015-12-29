package com.epam.runner;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.epam.data.HolidayReloader;

public class Runner {

	private final static Logger logger = Logger.getLogger(Runner.class);
	//local path 
	public static final String path = "file:/D:/github/JMP-2015-2016-Valiantsin_Charkashyn-Group-2/module5/jar/Loader-1.0.jar";

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		HolidayReloader loader = new HolidayReloader(HolidayReloader.class.getClassLoader());
		try {
			Class loadedClass = loader.loadClass("com.epam.congratulation.Congratulation");
			logger.debug("Choose method from menu:");
			loader.chooseMethod(loadedClass);

		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}

}
