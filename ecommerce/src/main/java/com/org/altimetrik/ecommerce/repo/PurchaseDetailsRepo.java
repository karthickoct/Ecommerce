package com.org.altimetrik.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.altimetrik.ecommerce.model.PurchaseDetails;

public interface PurchaseDetailsRepo extends JpaRepository<PurchaseDetails, Long>{
	
}