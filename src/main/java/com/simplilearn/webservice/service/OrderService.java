package com.simplilearn.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.webservice.bean.Orders;

import com.simplilearn.webservice.Repository.OrderRepo;
import com.simplilearn.webservice.utility.Items;

@Service
public class OrderService {

	
	@Autowired
	OrderRepo OrderRepo;
	
	public String placeOrder(List<Items> listOfItems,String user, float totalAmount) {
		String productNames ="";
		for(Items item : listOfItems) {
			productNames = productNames+","+item.getProduct().getProductName();
		}
		float balanceAmount = OrderRepo.getBalanceAmount(user);
		if(balanceAmount<totalAmount) {
			return "Insufficient amount"; 
		}else {
			if(OrderRepo.placeOrder(productNames.substring(1),user,totalAmount)>0) {
				return "Order Placed successfully";
			}else {
				return "Order didn't place";
			}
		}
		
	}
	
	public List<Orders> getAllOrderDetails(String email){
		return OrderRepo.getOrderDetails(email);
	}
}
