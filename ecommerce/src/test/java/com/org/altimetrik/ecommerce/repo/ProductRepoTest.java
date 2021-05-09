package com.org.altimetrik.ecommerce.repo;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.altimetrik.ecommerce.EcommerceApplication;
import com.org.altimetrik.ecommerce.model.GlobalEntity;
import com.org.altimetrik.ecommerce.model.Product;
import com.org.altimetrik.ecommerce.model.Stocks;
import com.org.altimetrik.ecommerce.repo.ProductRepo;
import com.org.altimetrik.ecommerce.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class ProductRepoTest {

	@Autowired
	private ProductRepo productRepo;
	private List<Product> productList = new ArrayList<>();
	@Before
	public void setUpData() throws ParseException {
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		
		Stocks stock = new Stocks();
		stock.totalQuantity = 20;
		stock.availableQuantity = 20;
		stock.stockUpdateOn = currentDate;
		
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.createdBy = "System";
		globalEntity.createdOn = currentDate;
		globalEntity.modifiedBy = "";
		globalEntity.modifiedOn = defaultDate;
		
		Product product = new Product();
		product.productType = "Book";
		product.category = "Sports";
		product.imported = false;
		product.price = 145.45;
		product.productName = "The Dhoni Touch: Unravelling the Enigma That Is Mahendra Singh Dhoni";
		product.stocks = stock;
		product.globalEntity = globalEntity;
		productList.add(product);
		
		product = new Product();
		product.productType = "Book";
		product.category = "Sports";
		product.imported = true;
		product.price = 1665.15;
		product.productName = "Bobby Fischer Teaches Chess Mass Market Paperback – Illustrated, 1";
		product.stocks = stock;
		product.globalEntity = globalEntity;
		productList.add(product);
		
		product = new Product();
		product.productType = "Book";
		product.category = "Management";
		product.imported = true;
		product.price = 2100.15;
		product.productName = "The 7 Habits of Highly Effective People";
		product.stocks = stock;
		product.globalEntity = globalEntity;
		productList.add(product);
	}
	
	@Test
	public void addProducts() {
		productRepo.saveAll(productList);
		assertNotNull(productRepo);
	}
}
