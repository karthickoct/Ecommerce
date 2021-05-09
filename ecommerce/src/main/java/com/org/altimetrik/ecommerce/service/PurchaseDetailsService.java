package com.org.altimetrik.ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.altimetrik.ecommerce.dto.response.ProductDetailsResponse;
import com.org.altimetrik.ecommerce.model.CartDetails;
import com.org.altimetrik.ecommerce.model.DiscountDetails;
import com.org.altimetrik.ecommerce.model.GlobalEntity;
import com.org.altimetrik.ecommerce.model.Product;
import com.org.altimetrik.ecommerce.model.PurchaseDetails;
import com.org.altimetrik.ecommerce.model.Stocks;
import com.org.altimetrik.ecommerce.repo.CartRepository;
import com.org.altimetrik.ecommerce.repo.DiscountDetailsRepo;
import com.org.altimetrik.ecommerce.repo.ProductRepo;
import com.org.altimetrik.ecommerce.repo.PurchaseDetailsRepo;
import com.org.altimetrik.ecommerce.util.CheckoutItems;

@Service
public class PurchaseDetailsService {

	@Autowired
	PurchaseDetailsRepo purchaseDetailsRepo;

	@Autowired
	DiscountDetailsRepo discountDetailsRepo;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepo productRepo;

	@Transactional
	public ProductDetailsResponse checkoutItems(CheckoutItems items){
		Date currentDate = new Date();
		ProductDetailsResponse productDetailService = new ProductDetailsResponse();
		PurchaseDetails purchaseDetails = new PurchaseDetails();
		CartDetails cartDetails = null;
		String userName = "";
		int availableQuantity = 0 ;
		double totalProductAmount = 0.00,discountAmount= 0.00,totalSaleTaxAmount = 0.00,totalImportTaxAmount=0.00,totalAmount = 0.00;
		if(!items.checkOutItems.isEmpty()) {
			for(Long cartsID:items.checkOutItems) {
				Optional<CartDetails> cartDetailsOpt  = cartRepository.findById(cartsID); 
				if(cartDetailsOpt.isPresent()) {
					cartDetails = cartRepository.getOne(cartsID);
					if(cartDetails.getPurchaseId() != 0L) {
						productDetailService.PurchaseId = 0L;
						productDetailService.errorCode = "PD005";
						productDetailService.errorDesc = "The cart was already purchased :"+cartsID;
						return productDetailService;
					}
					userName = cartDetails.getUserName();
					Product product = null;

					Optional<Product> productOpt = productRepo.findById(cartDetails.getProductId());
					if(productOpt.isPresent()) {
						product = productRepo.getOne(cartDetails.getProductId());
						availableQuantity = product.stocks.availableQuantity;

						if (availableQuantity < cartDetails.getQuantity()) {
							productDetailService.PurchaseId = 0L;
							productDetailService.errorCode = "PD003";
							productDetailService.errorDesc = "Kindly add the valid cart to proceed checkout";
							return productDetailService;
						} 

						totalSaleTaxAmount 	    += cartDetails.getSaleTaxAmount(); 
						totalProductAmount 	    += cartDetails.getProductAmount();
						totalImportTaxAmount 	+= cartDetails.getImportTaxAmount();
					}
				}else {
					productDetailService.PurchaseId = 0L;
					productDetailService.errorCode = "PD004";
					productDetailService.errorDesc = "Invalid cart id :"+cartsID;
					return productDetailService;
				}
			}

			totalAmount = totalProductAmount + totalSaleTaxAmount + totalImportTaxAmount;

			List<DiscountDetails> discountDetails = discountDetailsRepo.getPurchaseDiscountDetails(totalAmount);
			discountAmount = totalAmount * (discountDetails.get(0).discountPercentage / 100);

			totalAmount = totalAmount - discountAmount;

			GlobalEntity globalEntity = new GlobalEntity();
			globalEntity.createdBy = userName;
			globalEntity.createdOn = currentDate;
			globalEntity.modifiedBy = "";
			globalEntity.modifiedOn = currentDate;
			purchaseDetails.setGlobalEntity(globalEntity);
			purchaseDetails.setTotalImportTaxAmount(totalImportTaxAmount);
			purchaseDetails.setTotalProductAmount(totalProductAmount);
			purchaseDetails.setTotalSaleTaxAmount(totalSaleTaxAmount);
			purchaseDetails.setTotalAmount(totalAmount);
			purchaseDetails.setDiscountAmount(discountAmount);
			purchaseDetails.setTotalroundOffAmount(Math.round(totalAmount));
			purchaseDetails = purchaseDetailsRepo.save(purchaseDetails);

			cartRepository.updateByCartId(purchaseDetails.getPurchaseId(),items.checkOutItems);
			updateStock(items.checkOutItems);
			productDetailService.PurchaseId = purchaseDetails.getPurchaseId();
			productDetailService.errorCode = "PD001";
			productDetailService.errorDesc = "Purchase Details Added successfully";
		}else {
			productDetailService.PurchaseId = 0L;
			productDetailService.errorCode = "PD002";
			productDetailService.errorDesc = "No carts found to proceed for checkout";
		}
		return productDetailService;
	}

	@Transactional
	private void updateStock(List<Long> checkOutItems) {
		CartDetails cartDetails = null;
		if(checkOutItems != null) {
			Long purchaseId = 0L;
			for(Long cartsID:checkOutItems) {
				Optional<CartDetails> cartDetailsOpt  = cartRepository.findById(cartsID); 
				if(cartDetailsOpt.isPresent()) {
					cartDetails = cartRepository.getOne(cartsID);
					cartDetails.setPurchaseId(purchaseId);
					Product product = null;

					Optional<Product> productOpt = productRepo.findById(cartDetails.getProductId());
					if(productOpt.isPresent()) {
						product = productRepo.getOne(cartDetails.getProductId());
						Stocks stock = product.stocks;
						stock.availableQuantity = stock.availableQuantity - cartDetails.getQuantity();
						product.stocks = stock;
						productRepo.save(product);
					}
				}
			}
		}
	}
}