package com.simplilearn.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.webservice.Repository.CustomerRepo;
import com.simplilearn.webservice.bean.Customer;


@Service
public class CustomerService {

	@Autowired
	CustomerRepo CustomerRepo;
	
	public String storeCustomerDetails(Customer cc) {
		cc.setAmount(1000);
		if(CustomerRepo.storeCustomerDetails(cc)>0) {
			return "success";
		}else {
			return "failure";
		}
	}
	
	public float getBalanceDetails(String email) {
		return CustomerRepo.getBalanceDetails(email);
	}
	
	public String addAmount(String email, float amount) {
		if(CustomerRepo.addAmount(email, amount)>0) {
			return "Amount Added successfully";
		}else {
			return "Amount didn't add";
		}
	}
}
