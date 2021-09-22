package com.simplilearn.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.webservice.Repository.ProductRepo;
import com.simplilearn.webservice.bean.Product;
import com.simplilearn.webservice.utility.Items;

@Service
public class ProductService {

	@Autowired
	ProductRepo ProductRepo;
	
	public String stroreProductDetails(Product pr) {
	
		if(ProductRepo.storeProduct(pr)>0) {
			return "Product Details stored successfully";
		}else {
			return "Product Details didn't store successfully";
		}
	}
	
	public List<Product> getAllProduct(){
		return ProductRepo.getAllProduct();
	}
	
	public Product getProduct(int pid) {
		return ProductRepo.getProductById(pid);
	}
	
	public String udpateProductDetails(Product pro) {
		if(ProductRepo.updateProdutDetails(pro)>0) {
			return "Product Updted successfully";
		}else {
			return "Product didn't update";
		}
	}
	
	public String reduceQuantity(List<Items> items) {
		for(Items ii:items){
			Product p = ii.getProduct();
			int quantity = ii.getQuantity();
			int result = ProductRepo.reducerQuantity(p.getPid(), quantity);
			if(result==1) {
				return "success";
			}else {
				return "failure";
			}
		}
		return "done";
	}
}
