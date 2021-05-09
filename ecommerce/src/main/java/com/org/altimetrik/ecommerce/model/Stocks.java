package com.org.altimetrik.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stocks {
	@Column
	public int availableQuantity;
	@Column
	public int totalQuantity;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date stockUpdateOn;
}