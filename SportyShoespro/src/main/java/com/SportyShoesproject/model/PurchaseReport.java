package com.SportyShoesproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class PurchaseReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String categoryOfProduct; 
	
	private String productName;

	private String priceOfTheProduct;

	private String userWhoBoughtTheProduct;
	
	private String userEmailBoughtTheProduct;

	@Temporal(TemporalType.DATE)
	private Date dateOfProductPurchase;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryOfProduct() {
		return categoryOfProduct;
	}

	public void setCategoryOfProduct(String categoryOfProduct) {
		this.categoryOfProduct = categoryOfProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPriceOfTheProduct() {
		return priceOfTheProduct;
	}

	public void setPriceOfTheProduct(String priceOfTheProduct) {
		this.priceOfTheProduct = priceOfTheProduct;
	}

	public String getUserWhoBoughtTheProduct() {
		return userWhoBoughtTheProduct;
	}

	public void setUserWhoBoughtTheProduct(String userWhoBoughtTheProduct) {
		this.userWhoBoughtTheProduct = userWhoBoughtTheProduct;
	}

	public String getUserEmailBoughtTheProduct() {
		return userEmailBoughtTheProduct;
	}

	public void setUserEmailBoughtTheProduct(String userEmailBoughtTheProduct) {
		this.userEmailBoughtTheProduct = userEmailBoughtTheProduct;
	}

	

	public PurchaseReport(int productName2, String categoryOfProduct, String productPrice, String userName,
			String userWhoBoughtTheProduct, String date) {
		super();
		this.id = productName2;
		this.categoryOfProduct = categoryOfProduct;
		this.productName = productPrice;
		this.priceOfTheProduct = userName;
		this.userWhoBoughtTheProduct = userWhoBoughtTheProduct;
		this.userEmailBoughtTheProduct = date;
	}

	@Override
	public String toString() {
		return "PurchaseReport [id=" + id + ", categoryOfProduct=" + categoryOfProduct + ", productName=" + productName
				+ ", priceOfTheProduct=" + priceOfTheProduct + ", userWhoBoughtTheProduct=" + userWhoBoughtTheProduct
				+ ", userEmailBoughtTheProduct=" + userEmailBoughtTheProduct + "]";
	}

	public PurchaseReport() {
		super();
	}
	
}
