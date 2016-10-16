package com.javatpoint.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;


@ManagedBean(name = "person")
@RequestScoped
@XmlRootElement (name = "person")
public class Person {
	
	private int id;
	private String pname;
	private double salary;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id : " + this.id + '\n');
        stringBuilder.append("Name : " + this.pname + '\n');
        stringBuilder.append("Salary : " + this.salary + '\n');

        return stringBuilder.toString();
	}

}
