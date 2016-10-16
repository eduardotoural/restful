package com.javatpoint.restclient;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.javatpoint.aes.EncryptionController;
import com.javatpoint.model.Person;
import com.javatpoint.model.People;
import com.sun.jersey.api.representation.Form;

public class ClientPostTest {
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/RESTfulJerseyPath").build();
	}
	
	public void insertPerson(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI());
		Form form = new Form();
		
		form.add("key", EncryptionController.encrypt("mykey123"));
		form.add("id", "4");
		form.add("name", "Eduardo");
		form.add("price", "456");
		
		Response response = target.path("rest").path("product/add").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		System.out.println("Response " + response.getEntity());
	}
	
	public void deletePerson(){
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI());
	    
	    target.path("rest").path("product/delete/4").request().delete();
    }
		
	public void findPerson(){
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget webTarget = client.target(getBaseURI()).path("rest").path("product/find");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		 
		Person employee = response.readEntity(Person.class);
		     
		System.out.println(response.getStatus());
		System.out.println(employee);
		
	}
	
	public void listPerson(){
		
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI()).path("rest").path("product/listperson");
	   
	    Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_XML);
	    Response response = invocationBuilder.get();
		 
		People people = response.readEntity(People.class);
		List<Person> listOfPeople = people.getPeopleList();
		     
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfPeople.toArray(new Person[listOfPeople.size()]) ));
		 
	}
	
	public void allPeopleJson(){
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI()).path("rest").path("product/listpersonjson");
	    
	    Response response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).buildGet().invoke();

	    Gson gson = new Gson();
	    
	    People people = gson.fromJson(response.readEntity(String.class), People.class);
	    
	    System.out.println(people.getPeopleList().get(0).getPname());
	    //System.out.println(response.readEntity(String.class));
	}
	
	public void postPeopleJsonList(){
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI()).path("rest").path("product/postpeoplejson");
	    
	    People list = new People();
		
		Person p1 = new Person();
		p1.setId(1);
		p1.setPname("Angel");
		p1.setSalary(456);
		
		Person p2 = new Person();
		p2.setId(2);
		p2.setPname("Dani");
		p2.setSalary(534);
		
		Person p3 = new Person();
		p3.setId(1);
		p3.setPname("Valeria");
		p3.setSalary(867);
		
		list.getPeopleList().add(p1);
		list.getPeopleList().add(p2);
		list.getPeopleList().add(p3);
		
		Gson gson = new Gson();
	    String jsonString = gson.toJson(list);
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(jsonString, MediaType.APPLICATION_JSON));
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	
	public static void main(String[] args) {
		  
	  ClientPostTest cp = new ClientPostTest();
	  //cp.addPerson();
	  //cp.deleteEmployee();
	  //cp.createPerson();
	  //cp.listPerson();
	  
	  //cp.allPeopleJson();
	  
	  cp.postPeopleJsonList();
	  System.out.println("");
	}

  
}
