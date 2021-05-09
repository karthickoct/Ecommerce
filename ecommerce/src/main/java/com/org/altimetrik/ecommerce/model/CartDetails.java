package com.org.altimetrik.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CartDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;
	@Column
	private Long purchaseId;
	
	@Column
	private Long productId;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	@Column(length = 20, nullable = false)
	private String userName;
	@Column
	private double productAmount;
	@Column(length = 150, nullable = false)
	private String productName;
	@Column
	private int quantity;
	@Column
	private double saleTaxAmount;
	@Column
	private double importTaxAmount;
	@Embedded
	private GlobalEntity globalEntity;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Long getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSaleTaxAmount() {
		return saleTaxAmount;
	}
	public void setSaleTaxAmount(double saleTaxAmount) {
		this.saleTaxAmount = saleTaxAmount;
	}
	public double getImportTaxAmount() {
		return importTaxAmount;
	}
	public void setImportTaxAmount(double importTaxAmount) {
		this.importTaxAmount = importTaxAmount;
	}
	public GlobalEntity getGlobalEntity() {
		return globalEntity;
	}
	public void setGlobalEntity(GlobalEntity globalEntity) {
		this.globalEntity = globalEntity;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}