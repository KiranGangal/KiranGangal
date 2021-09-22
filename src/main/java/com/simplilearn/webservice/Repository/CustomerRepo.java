package com.simplilearn.webservice.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplilearn.webservice.bean.Customer;

@Repository
public class CustomerRepo {

	@Autowired
	EntityManagerFactory emf;
	
	public int storeCustomerDetails(Customer cc) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		tran.begin();
			manager.persist(cc);
		tran.commit();
		
		Customer c = manager.find(Customer.class, cc.getEmail());
		if(c!=null) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public float getBalanceDetails(String email) {
		EntityManager manager = emf.createEntityManager();
		Customer cc = manager.find(Customer.class,email);
		return cc.getAmount();
	}
	
	public int addAmount(String email, float amount) {
		EntityManager manager = emf.createEntityManager();
		Customer cc = manager.find(Customer.class,email);
		cc.setAmount(cc.getAmount()+amount);;
		EntityTransaction tran = manager.getTransaction();
		tran.begin();
			manager.merge(cc);
		tran.commit();
		
		if(cc.getAmount()>=amount) {
			return 1;
		}else {
			return 0;
		}
	}
}
