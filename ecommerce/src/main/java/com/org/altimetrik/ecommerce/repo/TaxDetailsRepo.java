package com.org.altimetrik.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.altimetrik.ecommerce.model.TaxDetails;

public interface TaxDetailsRepo extends JpaRepository<TaxDetails, Long>{
	
}