package com.epam.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.epam.factory.DatabaseFactory;
import com.epam.factory.FileFactory;
import com.epam.factory.ResourceFactory;
import com.epam.model.DatabaseResource;
import com.epam.model.Person;
import com.epam.model.Resource;

public class ResourceFacade {

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
	
	public static Person createPerson(){
		Person person = new Person();
		person.setName("Lama");
		person.setSurname("Petrova");
		person.setAge(21);
		return person;
	}
	
	public static void printResult(Person person){
		if(person==null){
			System.out.println("There are no person ");
		}else{
			System.out.println(person);
		}
	}
	
	public static ResourceFactory chooseFactory(){
		ResourceFactory factory = null;
		System.out.println("Do you want to work with file resource? yes/no");
		try{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String answer = bufferRead.readLine();
			if("yes".equalsIgnoreCase(answer)){
				factory = new FileFactory();
			}else{
				factory = new DatabaseFactory();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return factory;
	}
	
	public static void closeResource(Resource resource){
		if(resource instanceof DatabaseResource){
			((DatabaseResource)resource).closeConnection();
		}
	}

}
