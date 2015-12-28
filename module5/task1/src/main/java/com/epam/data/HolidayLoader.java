package com.epam.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class HolidayLoader {
	
	private final static Logger logger = Logger.getLogger(HolidayLoader.class);
	
	public Class loadCongratulationClass(String path) throws ClassNotFoundException, MalformedURLException{
		logger.debug("Load jar URLClassLoader");
		URL[] url = new URL[] { new URL(path) };
		URLClassLoader child = new URLClassLoader(url,
				HolidayLoader.class.getClassLoader());
		logger.debug("Load class by reflection");
		Class loadedClass = Class.forName(
				"com.epam.congratulation.Congratulation", true, child);
		return loadedClass;
	}
	
	public void chooseMethod(Class classToLoad) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, MalformedURLException {
		String result = null;
		boolean flag = true;
		while(flag){
			Menu.showMenu();
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			switch (choice) {
				case 1:{
					Method method = classToLoad.getDeclaredMethod("createNewYear");
					logger.debug("Work with method " + method.getName());
					Object instance = classToLoad.newInstance();
					result = (String) method.invoke(instance);
					logger.debug("Created " + result);
					break;
				}
				case 2:{
					Method method = classToLoad.getDeclaredMethod("createHoliday", new Class []{String.class, String.class, Integer.TYPE});
					logger.debug("Work with method " + method.getName());
					Object instance = classToLoad.newInstance();
					result = (String) method.invoke(instance, "Birthday", "July", 3);
					
					logger.debug("Created " + result);
					break;
				}
				case 3:{
					Method method = classToLoad.getDeclaredMethod("congratulate", new Class []{String.class});
					logger.debug("Work with method " + method.getName());
					
					Object instance = classToLoad.newInstance();
					if(result == null){
						method.invoke(instance, "new day");
					}else{
						method.invoke(instance, result);
					}
					break;
				}
				case 4:{
					flag = false;
					System.out.println("Bye");
					scanner.close();
					break;
				}
				default: {
					System.out.println("you made wrong choice");
				}
			}
		}
	}
}
