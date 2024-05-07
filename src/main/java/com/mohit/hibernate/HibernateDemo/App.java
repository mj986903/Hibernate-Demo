package com.mohit.hibernate.HibernateDemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class App 
{
    public static void main( String[] args )
    {
    	Alien mohit = new Alien();
		mohit.setAid(5000);
		mohit.setName("Mohit");
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
		ServiceRegistry registery = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).configure("hibernate.cfg.xml").build();
		SessionFactory sf = con.buildSessionFactory(registery);
		Session s = sf.openSession();	
		Transaction tx = s.beginTransaction();
		
		s.persist(mohit); // Insert Object Into Database
		
		Alien me = s.get(Alien.class, 50); // Fetch Object From Database
		
		Query q = s.createQuery("from Alien"); // Create Query Using HQL
		List<Alien>  list = q.list();
		for(Alien a : list) {
			System.out.println(a.toString());
		}
		
		Query q2 = s.createNativeQuery("select * from alien", Alien.class, "alien"); // Create Native Query
		List<Alien>  list2 = q2.list();
		for(Alien a : list2) {
			System.out.println(a.toString());
		}
		
		tx.commit();
    }
}
