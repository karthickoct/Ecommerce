package com.org.altimetrik.ecommerce.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.org.altimetrik.ecommerce.model.CartDetails;
import com.org.altimetrik.ecommerce.model.PurchaseDetails;
@Component
public class PrintReceiptDto {
	
	private List<CartDetails> cartDetails;
	private PurchaseDetails purchaseDetails;
	
	
	public List<CartDetails> getCartDetails() {
		return cartDetails;
	}
	public void setCartDetails(List<CartDetails> cartDetails) {
		this.cartDetails = cartDetails;
	}
	public PurchaseDetails getPurchaseDetails() {
		return purchaseDetails;
	}
	public void setPurchaseDetails(PurchaseDetails purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

}
