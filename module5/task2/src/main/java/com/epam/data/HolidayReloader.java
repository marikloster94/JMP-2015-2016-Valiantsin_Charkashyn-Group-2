package com.epam.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class HolidayReloader extends ClassLoader {

	private final static Logger logger = Logger.getLogger(HolidayReloader.class);

	public HolidayReloader(ClassLoader parent) {
		super(parent);
	}

	public Class loadClass(String name) throws ClassNotFoundException {
		if (!"com.epam.congratulation.Congratulation".equals(name)) {
			return super.loadClass(name);
		}
		logger.debug("Load jar URLClassLoader");
		try{
			String path = "file:/D:/github/JMP-2015-2016-Valiantsin_Charkashyn-Group-2/module5/jar/Loader-1.0.jar";
			URL[] url = new URL[] { new URL(path) };
			URLClassLoader child = new URLClassLoader(url,	HolidayReloader.class.getClassLoader());
			logger.debug("Reload class by reflection");
			Class loadedClass = Class.forName("com.epam.congratulation.Congratulation", true, child);
			return loadedClass;
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		return null;
	}

	public void chooseMethod(Class classToLoad) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, MalformedURLException {
		String result = null;
		boolean flag = true;
		while (flag) {
			Menu.showMenu();
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			switch (choice) {
			case 0:{
				classToLoad = loadClass("com.epam.congratulation.Congratulation");
				break;
			}
			case 1: {
				Method method = classToLoad.getDeclaredMethod("createNewYear");
				logger.debug("Work with method " + method.getName());
				Object instance = classToLoad.newInstance();
				result = (String) method.invoke(instance);
				logger.debug("Created " + result);
				break;
			}
			case 2: {
				Method method = classToLoad
						.getDeclaredMethod("createHoliday", new Class[] {
								String.class, String.class, Integer.TYPE });
				logger.debug("Work with method " + method.getName());
				Object instance = classToLoad.newInstance();
				result = (String) method
						.invoke(instance, "Birthday", "July", 3);

				logger.debug("Created " + result);
				break;
			}
			case 3: {
				Method method = classToLoad.getDeclaredMethod("congratulate",
						new Class[] { String.class });
				logger.debug("Work with method " + method.getName());

				Object instance = classToLoad.newInstance();
				if (result == null) {
					method.invoke(instance, "new day");
				} else {
					method.invoke(instance, result);
				}
				break;
			}
			case 4: {
				flag = false;
				System.out.println("Bye");
				scanner.close();
				break;
			}
			default: {
				System.out.println("You make wrong choice");
			}
			}
		}
	}
}
