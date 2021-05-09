package com.org.altimetrik.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.altimetrik.ecommerce.EcommerceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class PrintReceiptServiceTest {

	@Autowired
	private PrintReceiptService printReceiptService;
	
	@Test
	public void invalidPurchaseId() throws Exception {
		Long purchaseId = Long.valueOf(-1);
		assertEquals("Invalid Purchase Id", printReceiptService.printReceipt(purchaseId));
	}
	
	
	@Test
	public void validPurchaseId() throws Exception {
		Long purchaseId = Long.valueOf(23);
		assertNotEquals("Invalid Purchase Id", printReceiptService.printReceipt(purchaseId));
	}
}
