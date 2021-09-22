package com.simplilearn.webservice.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplilearn.webservice.bean.Customer;
import com.simplilearn.webservice.bean.Login;

@Repository
public class LoginRepo {

	
	@Autowired
	EntityManagerFactory emf;
	
	public int checkLoginDetails(Login ll) {
		
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select c from Customer c where c.email=?1 and c.password=?2");
		qry.setParameter(1, ll.getEmail());
		qry.setParameter(2, ll.getPassword());
		List<Customer> list= qry.getResultList();
		return list.size();
	}
}
