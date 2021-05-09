package com.org.altimetrik.ecommerce.model;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_id")
	private Long purchaseId;
	@Column
	private double totalProductAmount;
	@Column
	private double discountAmount;
	@Column
	private double totalSaleTaxAmount;
	@Column
	private double totalImportTaxAmount;
	
	@Column
	private double totalAmount;
	
	@Column
	private double totalroundOffAmount;
	
	@Embedded
	private GlobalEntity globalEntity;
	
	public Long getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}
	public double getTotalProductAmount() {
		return totalProductAmount;
	}
	public void setTotalProductAmount(double totalProductAmount) {
		this.totalProductAmount = totalProductAmount;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getTotalSaleTaxAmount() {
		return totalSaleTaxAmount;
	}
	public void setTotalSaleTaxAmount(double totalSaleTaxAmount) {
		this.totalSaleTaxAmount = totalSaleTaxAmount;
	}
	public double getTotalImportTaxAmount() {
		return totalImportTaxAmount;
	}
	public void setTotalImportTaxAmount(double totalImportTaxAmount) {
		this.totalImportTaxAmount = totalImportTaxAmount;
	}
	public GlobalEntity getGlobalEntity() {
		return globalEntity;
	}
	public void setGlobalEntity(GlobalEntity globalEntity) {
		this.globalEntity = globalEntity;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalroundOffAmount() {
		return totalroundOffAmount;
	}
	public void setTotalroundOffAmount(double totalroundOffAmount) {
		this.totalroundOffAmount = totalroundOffAmount;
	}
	
	
}