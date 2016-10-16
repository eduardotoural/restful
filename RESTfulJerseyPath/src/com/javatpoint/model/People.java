package com.javatpoint.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class People {
	
	@XmlElement(name="person")
	private List<Person> peopleList;
	
	public People(){
		peopleList = new ArrayList<Person>();
	}

	public List<Person> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<Person> peopleList) {
		this.peopleList = peopleList;
	}
	
	

}
