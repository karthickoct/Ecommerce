package com.org.altimetrik.ecommerce.service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.altimetrik.ecommerce.dto.PrintReceiptDto;
import com.org.altimetrik.ecommerce.model.CartDetails;
import com.org.altimetrik.ecommerce.model.PurchaseDetails;
import com.org.altimetrik.ecommerce.repo.CartRepository;
import com.org.altimetrik.ecommerce.repo.PrintReceiptRepo;


@Service
public class PrintReceiptService {

	@Autowired
	private PrintReceiptRepo printRepo;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private PrintReceiptDto printReceiptVO;

	// Create a new Locale
	Locale IND = new Locale("en", "IN");

	// Create a formatter given the Locale
	NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(IND);
	
	public String printReceipt(Long purchaseId)throws Exception {
		StringBuilder responseStr = new StringBuilder();
		Optional<PurchaseDetails> purchaseDetailOptional = printRepo.findById(purchaseId);
		if(purchaseDetailOptional.isPresent()) {
			printReceiptVO.setPurchaseDetails(purchaseDetailOptional.get());
			PurchaseDetails purchaseDetails = purchaseDetailOptional.get();
			Optional<List<CartDetails>> cartDetailsOptional= cartRepository.findByPurchaseId(purchaseId);
			if(cartDetailsOptional.isPresent()) {
				//printReceiptVO.setCartDetails(cartDetails.get());

				List<CartDetails> cartDetails = cartDetailsOptional.get();

				responseStr.append("PurchaseID : "+purchaseDetails.getPurchaseId()+"\n");
				responseStr.append("BookName   : Price \n");


				for (CartDetails cd : cartDetails) {
					responseStr.append(cd.getProductName()+"     "+cd.getProductAmount()+"\n");
				}

				responseStr.append("Discount Amount: "+rupeesFormat.format(purchaseDetails.getDiscountAmount())+"\n");
				responseStr.append("SaleTax Amount: "+rupeesFormat.format(purchaseDetails.getTotalSaleTaxAmount())+"\n");
				responseStr.append("ImportTax Amount: "+rupeesFormat.format(purchaseDetails.getTotalImportTaxAmount())+"\n");
				responseStr.append("Total Product Amount: "+rupeesFormat.format(Math.round(purchaseDetails.getTotalProductAmount()))+"\n");
				responseStr.append("\n");
				responseStr.append("\n");
				responseStr.append("*******Thank You !!!******* ");

			}else {
				return "Invalid Purchase Id";
			}
		}else {
			return "Invalid Purchase Id";
		}
		return responseStr.toString();
	}
}
