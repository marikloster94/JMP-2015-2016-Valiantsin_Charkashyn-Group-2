package com.epam.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public static String readFromProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;
		String result = null;
		try {
			String filename = "config.properties";
			input = ClassLoader.getSystemResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return result;
			}
			prop.load(input);
			result = prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
