package com.simplilearn.webservice.utility;

import com.simplilearn.webservice.bean.Product;

public class Items {
	
	private Product product=new  Product();
	
	private int quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Items(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Items() {
		super();
	}

	@Override
	public String toString() {
		return "Items [product=" + product + ", quantity=" + quantity + "]";
	}

}
