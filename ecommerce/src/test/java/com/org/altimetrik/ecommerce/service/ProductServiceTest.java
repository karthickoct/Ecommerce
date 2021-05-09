package com.org.altimetrik.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.altimetrik.ecommerce.EcommerceApplication;
import com.org.altimetrik.ecommerce.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void getProdut() {
		Long productId = Long.valueOf(1);
		assertEquals(0, productService.getProduct(productId).getResponseCode());
	}
	
	@Test
	public void getProdutNotFound() {
		Long productId = Long.valueOf(5);
		assertEquals(-2, productService.getProduct(productId).getResponseCode());
	}
	
	@Test
	public void getProductByProductName() {
		String productName = "Bobby Fischer Teaches Chess Mass Market Paperback – Illustrated, 1";
		assertEquals(0, productService.getProductByProductName(productName).getResponseCode());
	}
	
	@Test
	public void getProductByProductNameNotFound() {
		String productName = "Walter";
		assertEquals(-2, productService.getProductByProductName(productName).getResponseCode());
	}
	
	@Test
	public void getProductByCategory() {
		String category = "Sports";
		assertEquals(0, productService.getProductByCategory(category).getResponseCode());
	}
	
	@Test
	public void getProductByCategoryWithPagination() {
		String category = "Sports";
		int pageIndex = 0;
		int elementsPerPage = 1;
		assertEquals(0, productService.getProductByCategory(category, pageIndex, elementsPerPage).getResponseCode());
	}
	@Test
	public void getProductByCategoryWithPaginationOrderBy() {
		String category = "Sports";
		int pageIndex = 0;
		int elementsPerPage = 1;
		String orderBy = "asc";
		assertEquals(0, productService.getProductByCategory(category, pageIndex, elementsPerPage, orderBy).getResponseCode());
	}
	
	@Test
	public void getAllProducts() {
		assertEquals(0, productService.getAllProducts().getResponseCode());
	}
	
	@Test
	public void getAllProductsWithPaginationOrderBy() {
		int pageIndex = 0;
		int elementsPerPage = 1;
		String orderBy = "asc";
		assertEquals(0, productService.getAllProducts(pageIndex, elementsPerPage, orderBy).getResponseCode());
	}
	
	@Test
	public void getAllProductsWithPagination() {
		int pageIndex = 0;
		int elementsPerPage = 1;
		assertEquals(0, productService.getAllProducts(pageIndex, elementsPerPage).getResponseCode());
	}
}
