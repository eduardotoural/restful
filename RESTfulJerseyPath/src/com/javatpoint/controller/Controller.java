package com.javatpoint.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.javatpoint.dao.PersonDao;
import com.javatpoint.dao.PersonDaoImpl;
import com.javatpoint.model.Person;
import com.javatpoint.restclient.ClientPostTest;

@ManagedBean
@RequestScoped
public class Controller {
	
	
	@ManagedProperty(value="#{person}")
	private Person person;
	private PersonDao pd;
	private Person selectedPerson;
	
	private List<Person> personList;
	
	public Controller(){
		pd = new PersonDaoImpl();
		personList = pd.listPersons();
	}
	
	public Person getPerson(){
		return person;
	}
	
	public void setPerson(Person person){
		this.person = person;
	}
	
	
	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
	public void deleteRow(Person pperson){
		Iterator<Person> it = getPersonList().iterator();
		boolean found = true;
		while(it.hasNext() && !found){
			Person person = (Person)it.next();
			if(person.getId() == pperson.getId()){
				found = false;
			}
		}
	}

	/*public void testingPerson(){
		//System.out.println("Successfully charged"+getPerson());
		
		
		ClientPostTest cp = new ClientPostTest();
		cp.insertPerson(person);
		
	}*/
	
	public void deletePerson(Person person){
		pd.deletePerson(person);
		deleteRow(person);
	}

	public void insertPerson(){
		pd.insertPerson(person);
	}
	

}
