package com.org.altimetrik.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.org.altimetrik.ecommerce.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
	Optional<Product> findById(Long productId);
	Optional<List<Product>> findByProductNameContains(String productName);
	Optional<List<Product>> findByCategory(String category);
	Optional<List<Product>> findByCategory(String category, Pageable pageable);
	Page<Product> findAll(Pageable pageable);
}