package com.epam.factory.method;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.epam.model.FileResource;
import com.epam.model.Person;

public class FileFactoryMethod extends ResourceFactoryMethod {

	public FileFactoryMethod() {
		resource = new FileResource();
	}

	public void writePerson(Person person) {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					((FileResource) resource).getFile());
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(person);
			objectOut.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public Person readPerson() {
		Person obj = null;
		try {
			FileInputStream fileIn = new FileInputStream(
					((FileResource) resource).getFile().getAbsolutePath());
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			obj = (Person) objectIn.readObject();
			objectIn.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	public Person readPerson(String name) {
		Person obj = null;
		try {
			FileInputStream fileIn = new FileInputStream(
					((FileResource) resource).getFile().getAbsolutePath());
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			obj = (Person) objectIn.readObject();
			objectIn.close();
			if(obj.getName().equals(name)){
				return obj;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
