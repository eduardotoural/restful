package com.javatpoint.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.javatpoint.controller.HibernateConnector;
import com.javatpoint.model.Person;



public class PersonDaoImpl implements PersonDao {

	@Override
	public void insertPerson(Person person) {
		
		/*Configuration configuration = new Configuration();
		configuration.configure("/resources/hibernate.cfg.xml");
		SessionFactory buildSessionFactory = configuration.buildSessionFactory();
		Session session = buildSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(person);
		session.flush();
		transaction.commit();
		session.close();*/
		
		Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
            //return person;
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        } finally {
            session.close();
        }
	}

	/*@Override
	public void deletePerson(int id) {
		
		Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction beginTransaction = session.beginTransaction();
            Query createQuery = session.createQuery("delete from person s where s.id =:id");
            createQuery.setParameter("id", id);
            createQuery.executeUpdate();
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
	}*/
	
	@Override
	public void deletePerson(Person person) {
		
		Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction beginTransaction = session.beginTransaction();
            
            Person p = new Person();
            p.setId(person.getId());
            
            session.delete(p);
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersons() {
		List<Person> list = new ArrayList<Person>();
		
		Configuration configuration = new Configuration();
		configuration.configure("/resources/hibernate.cfg.xml");
		
		SessionFactory buildSessionFactory = configuration.buildSessionFactory();
		
		Session session = buildSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();	
			
		list = (List<Person>)session.createCriteria("from person").list();
		
		session.flush();
		transaction.commit();
		session.close();
		
		return list;
	}
	
	@Override
	public Person getPerson() {
		//List<Person> list = new ArrayList<Person>();
		
		Configuration configuration = new Configuration();
		configuration.configure("/resources/hibernate.cfg.xml");
		
		SessionFactory buildSessionFactory = configuration.buildSessionFactory();
		
		Session session = buildSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();	
			
		Person person = (Person) session.get(Person.class, 4);
		
		session.flush();
		transaction.commit();
		session.close();
		
		return person;
	}
	
	public Person personById(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from Student s where s.id = :id");
            query.setParameter("id", id);
 
            List<Person> queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                return (Person) queryList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<Person> listPersons() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from Person s");
 
            List<Person> queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<Person>) queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
	

}
