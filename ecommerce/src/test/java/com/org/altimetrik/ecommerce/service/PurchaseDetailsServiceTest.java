package com.org.altimetrik.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.altimetrik.ecommerce.EcommerceApplication;
import com.org.altimetrik.ecommerce.service.PurchaseDetailsService;
import com.org.altimetrik.ecommerce.util.CheckoutItems;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class PurchaseDetailsServiceTest {

	@Autowired
	private PurchaseDetailsService purchaseDetailsService;
	private CheckoutItems checkOutItems = new CheckoutItems();
	private List<Long> items = null;
	@Before
	public void setUpData() {
		items = new ArrayList<>();
		items.add(11L);
		items.add(12L);
		items.add(13L);
		items.add(14L);
		checkOutItems.checkOutItems = items;
	}
	
	@Test
	public void checkoutItems() {
		assertEquals("PD001", purchaseDetailsService.checkoutItems(checkOutItems).errorCode);
		assertEquals("Purchase Details Added successfully", purchaseDetailsService.checkoutItems(checkOutItems).errorDesc);
	}
	
	@Test
	public void checkItemNull() {
		items = null;
		checkOutItems.checkOutItems = items;
		assertEquals("PD002", purchaseDetailsService.checkoutItems(checkOutItems).errorCode);
		assertEquals("No carts found to proceed for checkout", purchaseDetailsService.checkoutItems(checkOutItems).errorDesc);
	}
	
	@Test
	public void checkStock() {
		assertEquals("PD003", purchaseDetailsService.checkoutItems(checkOutItems).errorCode);
		assertEquals("Kindly add the valid cart to proceed checkout", purchaseDetailsService.checkoutItems(checkOutItems).errorDesc);
	}
	
	@Test
	public void invalidCartId() {
		assertEquals("PD004", purchaseDetailsService.checkoutItems(checkOutItems).errorCode);
	}
	
	@Test
	public void alreadySoldedCart() {
		assertEquals("PD005", purchaseDetailsService.checkoutItems(checkOutItems).errorCode);
	}
	
}
