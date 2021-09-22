package com.simplilearn.webservice.Controller;

import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.simplilearn.webservice.bean.*;
import com.simplilearn.webservice.service.CustomerService;

@Controller
public class CustomerController {

	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="customerSignUp",method = RequestMethod.GET)
	public ModelAndView signUpCustomer() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customerSignUp.html");
		return mav;
	}
	
	@RequestMapping(value="customerDashboard",method = RequestMethod.GET)
	public ModelAndView customerDashboard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customerHome.html");
		return mav;
	}
	
	@RequestMapping(value="balanceCheck/{email}",method = RequestMethod.GET)
	public ModelAndView balaceCheck(@PathVariable("email") String email,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		float amount = customerService.getBalanceDetails(email);
		session.setAttribute("amount", amount);
		mav.setViewName("redirect:/addAmount.html");
		return mav;
	}
	
	
	@RequestMapping(value="addAmount",method = RequestMethod.POST)
	public ModelAndView addAmount(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String email = (String)session.getAttribute("user");
		float newAmount = Float.parseFloat(req.getParameter("amount"));
		String addAmountMsg = customerService.addAmount(email, newAmount);
		float amount = customerService.getBalanceDetails(email);
		session.setAttribute("amount", amount);
		session.setAttribute("amountAddMsg", addAmountMsg);
		mav.setViewName("redirect:/addAmount.html");
		return mav;
	}
	
	@RequestMapping(value="customerCreate",method = RequestMethod.POST)
	public ModelAndView customerAccountCreate(HttpServletRequest req,HttpSession hs) {
		System.out.println("In customer created");
		ModelAndView mav = new ModelAndView();
		Customer cc = new Customer();
		System.out.println(req.getParameter("dob"));
		cc.setFirstName(req.getParameter("firstName"));
		cc.setLastName(req.getParameter("lastName"));
		cc.setGender(req.getParameter("gender"));
		cc.setDob(Date.valueOf(req.getParameter("dob")));
		cc.setPhoneNumber(Long.parseLong(req.getParameter("phoneNumber")));
		cc.setEmail(req.getParameter("email"));
		cc.setPassword(req.getParameter("password"));
		String result = customerService.storeCustomerDetails(cc);
		if(result.equals("success")) {
			mav.addObject("msg", "Account Created successfully");
		}else {
			mav.addObject("msg", "Account didn't create");
		}
		mav.setViewName("customerSignUp.html");
		return mav;
	}
	
	
}
