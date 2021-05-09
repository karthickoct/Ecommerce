package com.org.altimetrik.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.altimetrik.ecommerce.dto.response.ProductDetailsResponse;
import com.org.altimetrik.ecommerce.service.PurchaseDetailsService;
import com.org.altimetrik.ecommerce.util.CheckoutItems;

@RestController
@RequestMapping(value = "/api")
public class PurchaseDetailsController {
	
	@Autowired
	PurchaseDetailsService purchaseService;
	
	@PostMapping(value = "/checkout")
	public ProductDetailsResponse addCheckout(@RequestBody CheckoutItems items) {
		return purchaseService.checkoutItems(items);
	}
	
	
}