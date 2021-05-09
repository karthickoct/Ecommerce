package com.org.altimetrik.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.altimetrik.ecommerce.dto.response.ProductResponseDto;
import com.org.altimetrik.ecommerce.service.ProductService;

@RestController
@RequestMapping(value = "/api")
public class EcommerceController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/")
	public String home() {
		return "Ecommerce Application is up and running";
	}
	
	@GetMapping(value = "/search/productId/{productId}")
	public ProductResponseDto getProduct(@PathVariable Long productId) {
		return productService.getProduct(productId);
	}
	@GetMapping(value = "/search/productName/{productName}")
	public ProductResponseDto getProductByProductName(@PathVariable String productName) {
		return productService.getProductByProductName(productName);
	}
	@GetMapping(value = "/search/productCategory/{productCategory}")
	public ProductResponseDto getProductByCategory(@PathVariable String productCategory) {
		return productService.getProductByCategory(productCategory);
	}
	@GetMapping(value = "/search/productCategory/{productCategory}/pageIndex/{pageIndex}/elementsPerPage/{elementsPerPage}/ordreBy{ordreBy}")
	public ProductResponseDto getProductByCategory(@PathVariable String productCategory,
			@PathVariable int pageIndex, @PathVariable int elementsPerPage, @PathVariable String ordreBy) {
		return productService.getProductByCategory(productCategory, pageIndex, elementsPerPage, ordreBy);
	}
	@GetMapping(value = "/search/productCategory/{productCategory}/pageIndex/{pageIndex}/elementsPerPage/{elementsPerPage}")
	public ProductResponseDto getProductByCategory(@PathVariable String productCategory,@PathVariable int pageIndex, @PathVariable int elementsPerPage) {
		return productService.getProductByCategory(productCategory, pageIndex, elementsPerPage); 
	}
	@GetMapping(value = "/search")
	public ProductResponseDto getAllProducts() {
		return productService.getAllProducts();
	}
	@GetMapping(value = "/search/pageIndex/{pageIndex}/elementsPerPage/{elementsPerPage}/ordreBy{ordreBy}")
	public ProductResponseDto getAllProducts(int pageIndex, int elementsPerPage, String ordreBy) {
		return productService.getAllProducts(pageIndex, elementsPerPage, ordreBy);
	}
	public ProductResponseDto getAllProducts(int pageIndex, int elementsPerPage) {
		return productService.getAllProducts(pageIndex, elementsPerPage);
	}
}