package com.javatpoint.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
    @Path("/add")  
    public Response addUser(
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
		
		return Response.status(200)  
	            .entity(returnResponse)  
	            .build();  
		
		//p.deletePerson(4);
		
    }  
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteEmployee(@PathParam("id") int id){
        
        Person person = new Person();
        person.setId(id);
		person.setPname("Aria");
		person.setSalary(345);
        
        PersonDao pd = new PersonDaoImpl();
        //int count = pd.deletePerson(person);
        int count = 1;
        pd.deletePerson(person);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    
    
    // This method is called if XML is request
    @GET
    @Path("/todos")
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
    	
    	PersonDao p = new PersonDaoImpl();
    	List<Person> list = p.listPersons();
    	
    	//Person person = p.getPerson();
    	
    	/*JAXBContext jaxbContext;
    	Marshaller jaxbMarshaller;
    	
		try {
			jaxbContext = JAXBContext.newInstance(Person.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
       
      return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
		
		//return Response.ok(list).build();
    }
    
    public Map<Integer, Person> getList() {
		return list;
	}

	public void setList(Map<Integer, Person> list) {
		this.list = list;
	}

	@POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    public Person createPerson(Person person){
    	person.setId(idCounter.getAndIncrement());
    	list.put(person.getId(), person);
    	
    	return person;
    }
	
	@GET
	@Path("/find")
    @Produces({ MediaType.APPLICATION_XML})
    //public Response findPerson(@QueryParam("id") String id){
	public Response findPerson(){
		
	    Person emp = new Person();
	     
	    emp.setId(Integer.parseInt("1"));
	    emp.setPname("Lokesh Gupta");
	     
	    GenericEntity<Person> entity = new GenericEntity<Person>(emp, Person.class);
	    return Response.ok().entity(entity).build();
		
		//return list.get(Integer.parseInt(id));
	}
	
	@GET
    @Path("/oneperson")
    @Produces({ MediaType.APPLICATION_JSON })
	public String onePerson(){
		//return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
		
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id : " + 3 + '\n');
        stringBuilder.append("Author : " + "Eduardo" + '\n');
        stringBuilder.append("Price : " + 234 + '\n');

        return stringBuilder.toString();
	}
	
	@GET
	@Path("/listperson")
	@Produces(MediaType.APPLICATION_XML)
	public Response listPerson(){
		
		People list = new People();
		
		//List<Person> mylist = new ArrayList<Person>();
		
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
		
		//mylist.add(p1);
		//mylist.add(p2);
		//mylist.add(p3);
		
		GenericEntity<People> entity = new GenericEntity<People>(list, People.class);
	    return Response.ok().entity(entity).build();
		
		//return list;
	}
	
	@GET
	@Path("/listpersonjson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPersonjson(){
		
		People list = new People();
		
		//List<Person> mylist = new ArrayList<Person>();
		
		Gson gson = new Gson();
		
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
		
		//mylist.add(p1);
		//mylist.add(p2);
		//mylist.add(p3);
		
		//GenericEntity<People> entity = new GenericEntity<People>(list, People.class);
	    //return Response.ok().entity(entity).build();
	    
	    
	    String jsonString = gson.toJson(list);
	    return Response.status(Response.Status.OK).entity(jsonString).build();
		
		//return list;
	}
    
    
}  
