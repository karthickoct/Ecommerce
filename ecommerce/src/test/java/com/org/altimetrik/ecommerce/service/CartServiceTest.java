package com.org.altimetrik.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.altimetrik.ecommerce.EcommerceApplication;
import com.org.altimetrik.ecommerce.dto.CartRequestDto;
import com.org.altimetrik.ecommerce.service.CartService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class CartServiceTest {

	private CartRequestDto vo = new CartRequestDto();
	
	@Autowired
	private CartService cartService;
	
	@Before
	public void setUpData() {
		vo.setProductId(1L);
		vo.setQuantity(1);
		vo.setUserName("Karthik");
	}
	
	@Test
	public void addToCart() throws Exception {
		assertEquals("CA001", cartService.addToCart(vo).errorCode);
		assertEquals("Cart Added Successfully", cartService.addToCart(vo).errorDesc);
	}
	
	@Test
	public void stockNotAvailable() throws Exception {
		vo.setQuantity(100);
		assertEquals("CA003", cartService.addToCart(vo).errorCode);
		assertEquals("No stock available for this product", cartService.addToCart(vo).errorDesc);
	}
	
	@Test
	public void productNotAvailable() throws Exception {
		vo.setProductId(5L);
		assertEquals("CA002", cartService.addToCart(vo).errorCode);
		assertEquals("Product not available", cartService.addToCart(vo).errorDesc);
	}
}
