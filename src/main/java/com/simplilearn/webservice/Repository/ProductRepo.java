package com.simplilearn.webservice.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplilearn.webservice.bean.Product;

@Repository
public class ProductRepo {

	
	@Autowired
	EntityManagerFactory emf;
	
	public int storeProduct(Product pr) {
		
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		tran.begin();
			manager.persist(pr);
		tran.commit();
		
		Product p = manager.find(Product.class, pr.getPid());
		if(p!=null) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
	public List<Product> getAllProduct() {
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select p from Product p");
		return qry.getResultList();
	}
	
	public Product getProductById(int pid) {
		EntityManager manager = emf.createEntityManager();
		Product p = manager.find(Product.class, pid);
		if(p!=null) {
			return p;
		}else {
			return null;
		}
	}
	
	public 	int updateProdutDetails(Product pr) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
				
		Product p = manager.find(Product.class, pr.getPid());
		if(p!=null) {
			tran.begin();
			p.setProductPrice(pr.getProductPrice());
			p.setProductQuantity(pr.getProductQuantity());
			manager.merge(p);
			tran.commit();
			return 1;
		}else {
			return 0;
		}	
	}
	
	public int reducerQuantity(int pid, int quantity) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		Product p = manager.find(Product.class, pid);
		if(p.getProductQuantity()>=quantity) {
			tran.begin();
				p.setProductQuantity(p.getProductQuantity()-quantity);
				manager.merge(p);
			tran.commit();
			return 1;
		}else {
			return 0;
		}
		
	}
}
