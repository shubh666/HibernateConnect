package com.hbm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hbm.dto.Employee;

public class Main {
	
	private static SessionFactory sf;
	
	public static void main(String[] args) 
	{
		Configuration cg = new Configuration().addAnnotatedClass(Employee.class).configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cg.getProperties());
		
		StandardServiceRegistry ssr = ssrb.build();
		sf = cg.buildSessionFactory(ssr);
		
		//Save();
		SelectAll();
	}
	
	private static void SelectAll()
	{
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("from Employee");
		List<Employee> elist = q.list();
		for(Employee e:elist)
		{
			System.out.println(e);
		}
		tx.commit();
		s.close();
	}
	
	private static void Save()
	{
		Employee e = new Employee();
		e.setEmpId(3);
		e.setEmpName("Darshan");
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		s.save(e);
		tx.commit();
		s.close();
	}
}
