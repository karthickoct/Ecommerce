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
public class TaxDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long taxId;
	@Column(length = 15, nullable = false)
	public String taxType;
	@Column(length = 150, nullable = false)
	public String taxDescription;
	@Column
	public float taxPercentage;
	@Embedded
	public GlobalEntity globalEntity;
}