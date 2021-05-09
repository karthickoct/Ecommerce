package com.org.altimetrik.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.altimetrik.ecommerce.model.DiscountDetails;

@Repository
public interface DiscountDetailsRepo extends JpaRepository<DiscountDetails, Long> {
	
	@Query(value = "select * from ecommerce.discount_details where discount_limit <= ?1 order by discount_limit desc",nativeQuery = true)
    List<DiscountDetails> getPurchaseDiscountDetails(double purchaseAmount);

}
