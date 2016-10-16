package com.javatpoint.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;  
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.javatpoint.aes.EncryptionController;
import com.javatpoint.dao.PersonDao;
import com.javatpoint.dao.PersonDaoImpl;
import com.javatpoint.model.People;
import com.javatpoint.model.Person;

@Path("/product")  
public class ProductService{  
	private String returnResponse = "";
	private AtomicInteger idCounter = new AtomicInteger();
	private Map<Integer, Person> list = new HashMap<Integer, Person>();
	
	public ProductService(){
		 	Person person1 = new Person();
	        person1.setId(idCounter.getAndIncrement());
	        person1.setPname("MargaretWeis");
	        person1.setSalary(7.99);

	        Person person2 = new Person();
	        person2.setId(idCounter.getAndIncrement());
	        person2.setPname("Salvatore");
	        person2.setSalary(9.99);

	        list.put(person1.getId(), person1);
	        list.put(person2.getId(), person2);
	}
	
    @POST  
    @Path("/insertperson")  
    public Response insertPerson(
    	@FormParam("key") String key,
        @FormParam("id") int id,  
        @FormParam("name") String name,  
        @FormParam("price") double price) {  
    	
		Person person = new Person();
		person.setId(id);
		person.setPname(name);
		person.setSalary(price);
		
		if(EncryptionController.decrypt(key).equalsIgnoreCase("mykey123")){
			PersonDao p = new PersonDaoImpl();
			p.insertPerson(person);
			
			returnResponse = " Product added successfuly!<br> Id: "+id+"<br> Name: " + name+"<br> Price: "+price;
		}
		else{
			returnResponse = " Wrong key, Denied Access ";
		}
		return Response.status(200).entity(returnResponse).build();  
    }  
    
    @POST
	@Path("/addpeoplelist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPeopleList(String jsonpeople){
		
		Gson gson = new Gson();
		People list = gson.fromJson(jsonpeople, People.class);
		
	    return Response.status(Response.Status.OK).entity(jsonpeople).build();
	}
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deletePerson(@PathParam("id") int id){
        
        Person person = new Person();
        person.setId(id);

        PersonDao pd = new PersonDaoImpl();
        //int count = pd.deletePerson(person);
        int count = 1;
        pd.deletePerson(person);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @GET
	@Path("/findperson/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
	public Response findPerson(@PathParam("id") Integer id){
		
	    Person person = new Person();
	     
	    person.setId(id);
	    person.setPname("Lokesh Gupta");
	
	    Gson gson = new Gson();
	    
	    String jsonString = gson.toJson(person);
	    
	    return Response.status(Response.Status.OK).entity(jsonString).build();
	}
    
    @GET
	@Path("/allpeople")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allPeople(){
		
		People list = new People();
		
		Person p1 = new Person();
		p1.setId(1);
		p1.setPname("Eduardo");
		p1.setSalary(234);
		
		Person p2 = new Person();
		p2.setId(2);
		p2.setPname("Aria");
		p2.setSalary(345);
		
		Person p3 = new Person();
		p3.setId(1);
		p3.setPname("Jenna");
		p3.setSalary(645);
		
		list.getPeopleList().add(p1);
		list.getPeopleList().add(p2);
		list.getPeopleList().add(p3);
		
		Gson gson = new Gson();
	    String jsonString = gson.toJson(list);
	    
	    return Response.status(Response.Status.OK).entity(jsonString).build();
	}

	public Map<Integer, Person> getList() {
		return list;
	}

	public void setList(Map<Integer, Person> list) {
		this.list = list;
	}
    
}  
