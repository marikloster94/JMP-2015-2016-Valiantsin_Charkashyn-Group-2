package com.epam.service.jaxb.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.service.model.Person;

@XmlRootElement(name = "persons")
@XmlAccessorType (XmlAccessType.FIELD)
public class Persons {
	@XmlElement(name = "person")
    private List<Person> persons = new ArrayList<Person>();
 
    public List<Person> getPersons() {
        return persons;
    }
 
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}