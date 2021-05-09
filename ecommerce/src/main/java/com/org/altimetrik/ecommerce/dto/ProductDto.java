package com.org.altimetrik.ecommerce.dto;

import com.org.altimetrik.ecommerce.model.Stocks;

public class ProductDto {
	private Long productId;
	private String productType;
	private String productName;
	private double price;
	private boolean imported;
	private String category;
	private Stocks stocks;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isImported() {
		return imported;
	}
	public void setImported(boolean imported) {
		this.imported = imported;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Stocks getStocks() {
		return stocks;
	}
	public void setStocks(Stocks stocks) {
		this.stocks = stocks;
	}
}