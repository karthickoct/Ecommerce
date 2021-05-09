package com.org.altimetrik.ecommerce.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.altimetrik.ecommerce.dto.CartRequestDto;
import com.org.altimetrik.ecommerce.dto.CartResponseDto;
import com.org.altimetrik.ecommerce.model.CartDetails;
import com.org.altimetrik.ecommerce.model.GlobalEntity;
import com.org.altimetrik.ecommerce.model.Product;
import com.org.altimetrik.ecommerce.model.TaxDetails;
import com.org.altimetrik.ecommerce.repo.CartRepository;
import com.org.altimetrik.ecommerce.repo.ProductRepo;
import com.org.altimetrik.ecommerce.repo.TaxDetailsRepo;
import com.org.altimetrik.ecommerce.util.Constants;


@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private TaxDetailsRepo taxDetailsRepo;
	
	public CartResponseDto addToCart(CartRequestDto request) throws Exception {
		CartResponseDto cartResponse = new CartResponseDto();
		
		Product product = null;
		CartDetails cartDetails = new CartDetails();
		CartDetails cartDetailsDB = null;
		List<CartDetails> cartDetailsListDB = null;
		List<TaxDetails> taxDetailsTemp = null;
		int availableQuantity = 0;
		int quantity = 0;
		float saleTaxPer = 0;
		float importTaxPer = 0;
		
		double saleTaxAmount = 0;
		double importTaxAmount = 0;
		
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.createdBy = request.getUserName();
		globalEntity.createdOn = currentDate;
		globalEntity.modifiedBy = "";
		globalEntity.modifiedOn = defaultDate;
		
		Optional<Product> productOpt = productRepo.findById(request.getProductId());
		if(productOpt.isPresent()) {
			product = productRepo.getOne(request.getProductId());
			
			availableQuantity = product.stocks.availableQuantity;
			
			cartDetailsListDB = cartRepository.findByProductIdAndUserName(request.getProductId(), request.getUserName());
			if (!cartDetailsListDB.isEmpty()) {
				cartDetailsDB = cartDetailsListDB.get(0);
				quantity = cartDetailsDB.getQuantity() + request.getQuantity();
				
				// If already available Product under same User update quantity with CardId
				cartDetails.setCartId(cartDetailsDB.getCartId());
			} else {
				quantity = request.getQuantity();
			}
			
			// To check the available quantity to add cart
			if (availableQuantity >= quantity) {
				List<TaxDetails> taxDetailsList = taxDetailsRepo.findAll();
				
				// Get Sale Tax Percentage 
				taxDetailsTemp = taxDetailsList.stream().filter(taxDetail -> taxDetail.taxType.equalsIgnoreCase("SALE_TAX")).collect(Collectors.toList());
				if (!taxDetailsTemp.isEmpty()) {
					saleTaxPer = taxDetailsTemp.get(0).taxPercentage;
				}
				
				// Get Import tax percentage if Product is Imported
				if (product.imported) {
					taxDetailsTemp = taxDetailsList.stream().filter(taxDetail -> taxDetail.taxType.equalsIgnoreCase("IMPORT_TAX")).collect(Collectors.toList());
					if (!taxDetailsTemp.isEmpty()) {
						importTaxPer = taxDetailsTemp.get(0).taxPercentage;
					}
				}
				
				// Calculate Sale & Import Tax
				saleTaxAmount = product.price * saleTaxPer / 100;
				importTaxAmount = product.price * importTaxPer / 100;
				
				
				cartDetails.setPurchaseId(0L); //Default Purcharse ID value is 0 (ie only added in Cart not pursed)
				cartDetails.setUserName(request.getUserName());
				cartDetails.setOrderDate(defaultDate);
				cartDetails.setProductName(product.productName);
				cartDetails.setProductAmount(product.price);
				cartDetails.setQuantity(quantity);
				cartDetails.setSaleTaxAmount(saleTaxAmount);
				cartDetails.setImportTaxAmount(importTaxAmount);
				cartDetails.setProductId(request.getProductId());
				cartDetails.setGlobalEntity(globalEntity);
				
				// Save or Update Cart Details
				cartDetails = cartRepository.save(cartDetails);
				
				cartResponse.cartId = cartDetails.getCartId();
				cartResponse.errorCode = "CA001";
				cartResponse.errorDesc = "Cart Added Successfully";
			} else {
				cartResponse.cartId = 0L;
				cartResponse.errorCode = "CA003";
				cartResponse.errorDesc = "No stock available for this product";
			}
		} else {
			cartResponse.cartId = 0L;
			cartResponse.errorCode = "CA002";
			cartResponse.errorDesc = "Product not available";
		}
		
		return cartResponse;
	}
	
	public List<CartDetails> findByUserName(String userName) {
		Optional<List<CartDetails>> optionalCartDetails = cartRepository.findByUserName(userName);
		if(optionalCartDetails.isPresent()) {
			return optionalCartDetails.get();
		} else {
			return new ArrayList<>();
		}
	}
}
