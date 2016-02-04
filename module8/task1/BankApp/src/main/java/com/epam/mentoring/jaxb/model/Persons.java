package com.epam.mentoring.jaxb.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.mentoring.model.Person;

@XmlRootElement(name = "clients")
@XmlAccessorType (XmlAccessType.FIELD)
public class Persons {
	@XmlElement(name = "person")
    private List<Person> clients = new ArrayList<Person>();
 
    public List<Person> getClients() {
        return clients;
    }
 
    public void setPersons(List<Person> clients) {
        this.clients = clients;
    }
}
