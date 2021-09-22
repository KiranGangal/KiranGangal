package com.simplilearn.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.webservice.Repository.LoginRepo;
import com.simplilearn.webservice.bean.Login;


@Service
public class LoginService {

	@Autowired
	LoginRepo LoginRepo;
	
	public String checkAdminUser(Login login) {
		if(login.getEmail().equals("admin@gmail.com") && login.getPassword().equals("admin")) {
			return "success";
		}else {
			return "failure";
		}
	}
	
	public String checkCustomerUser(Login login) {
		if(LoginRepo.checkLoginDetails(login) > 0) {
			return "success";
		}else {
			return "failure";
		}
		
	}
}
