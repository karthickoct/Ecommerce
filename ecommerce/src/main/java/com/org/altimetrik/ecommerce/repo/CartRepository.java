package com.org.altimetrik.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.altimetrik.ecommerce.model.CartDetails;

@Repository
public interface CartRepository extends JpaRepository<CartDetails, Long> {

	@Query("from CartDetails where purchaseId = 0 and userName in (:userName)")
	Optional<List<CartDetails>> findByUserName(@Param("userName") String userName);
	Optional<List<CartDetails>> findByPurchaseId(Long purchaseID);
    @Transactional
	@Modifying
	@Query("update CartDetails set purchaseId = :purchaseId where cartId in (:cartId)")
	void updateByCartId(@Param("purchaseId") Long purchaseId,@Param("cartId") List<Long> cartId);
	List<CartDetails> findByProductIdAndUserName(Long productId, String userName);
}
