package com.org.altimetrik.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long discountId;
	@Column
	public double discountLimit;
	@Column
	public double discountPercentage;
	@Embedded
	public GlobalEntity globalEntity;
	
}