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
import org.glassfish.jersey.filter.LoggingFilter;

import com.javatpoint.aes.EncryptionController;
import com.javatpoint.model.Person;
import com.javatpoint.model.People;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.representation.Form;

public class ClientPostTest {
	
	public void deleteEmployee(){
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI());
	    
	    target.path("rest").path("product/delete/4").request().delete();
    }
	
	public void addPerson(){
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
	
	private static URI getBaseURI() {
		  return UriBuilder.fromUri("http://localhost:8080/RESTfulJerseyPath").build();
	}
	
	public void createPerson(){
		
        /*ClientConfig config = new ClientConfig();
       
	    Client client = ClientBuilder.newClient(config);
	    //WebTarget target = client.target(getBaseURI()).path("rest").path("product/find").path("1");
	    WebTarget target = client.target(getBaseURI());
	    Invocation.Builder invocationBuilder =  target.request(MediaType.TEXT_XML);
	    //Response response = invocationBuilder.get();
	    
	    Response response = target.path("rest").path("product/find").path("1").request().accept(MediaType.TEXT_XML).get(Response.class);
	      
		Person person = response.readEntity(Person.class);
		
		System.out.println(person.toString());*/
		
		
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
	    
	    //Invocation.Builder invocationBuilder = target.path("rest").path("product/listperson").request().accept(MediaType.APPLICATION_XML);
		
	    Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_XML);
	    Response response = invocationBuilder.get();
		 
		People people = response.readEntity(People.class);
		List<Person> listOfPeople = people.getPeopleList();
		     
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfPeople.toArray(new Person[listOfPeople.size()]) ));
		 
		
	}
	
		public void listPersonjson(){
		
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI()).path("rest").path("product/listpersonjson");
	    
	    
	    //Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
	    //Response response = invocationBuilder.get();
	    
	    Response response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).buildGet().invoke();
		 
		//People people = response.readEntity(People.class);
		//List<Person> listOfPeople = people.getPeopleList();
		     
		//System.out.println(response.getStatus());
		//System.out.println(Arrays.toString( listOfPeople.toArray(new Person[listOfPeople.size()]) ));
	    
	    System.out.println(response.readEntity(String.class));
		 
		
	}
	
	public static void main(String[] args) {
		  
	  ClientPostTest cp = new ClientPostTest();
	  //cp.addPerson();
	  //cp.deleteEmployee();
	  //cp.createPerson();
	  //cp.listPerson();
	  cp.listPersonjson();
	  System.out.println("");
	  
	}

  
}
