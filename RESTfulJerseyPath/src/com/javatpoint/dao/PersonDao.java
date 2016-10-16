package com.javatpoint.dao;

import java.util.List;

import com.javatpoint.model.Person;

public interface PersonDao {
	
	void insertPerson(Person person);
	void deletePerson(Person person);
	List<Person> getPersons();
	
	Person personById(int id);
	List<Person> listPersons();
	Person getPerson();


}
