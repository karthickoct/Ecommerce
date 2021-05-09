package com.org.altimetrik.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.org.altimetrik.ecommerce.dto.ProductDto;
import com.org.altimetrik.ecommerce.dto.response.ProductResponseDto;
import com.org.altimetrik.ecommerce.model.Product;
import com.org.altimetrik.ecommerce.repo.ProductRepo;
import com.org.altimetrik.ecommerce.service.MessageByLocaleService;
import com.org.altimetrik.ecommerce.util.Constants;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private MessageByLocaleService localResolver;

	public ProductResponseDto getProduct(Long productId) {
		Optional<Product> productOptional = productRepo.findById(productId);
		ProductResponseDto response = new ProductResponseDto();
		List<ProductDto> serviceData = new ArrayList<>();
		if(productOptional.isPresent()) {
			serviceData.add(mapProduct(productOptional.get()));
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}

	
	public ProductResponseDto getProductByProductName(String productName) {
		Optional<List<Product>> productOptional = productRepo.findByProductNameContains(productName);
		ProductResponseDto response = new ProductResponseDto();
		if(productOptional.isPresent()) {
			response.setServiceData(mapProductList(productOptional.get()));
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(new ArrayList<>());
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}

	
	public ProductResponseDto getProductByCategory(String category) {
		Optional<List<Product>> productOptional = productRepo.findByCategory(category);
		ProductResponseDto response = new ProductResponseDto();
		List<ProductDto> serviceData = new ArrayList<>();
		if(productOptional.isPresent()) {
			response.setServiceData(mapProductList(productOptional.get()));
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}

	
	public ProductResponseDto getProductByCategory(String category, int pageIndex, int elementsPerPage) {
		Optional<List<Product>> productOptional = productRepo.findByCategory(category,
				PageRequest.of(pageIndex, elementsPerPage));
		ProductResponseDto response = new ProductResponseDto();
		List<ProductDto> serviceData = new ArrayList<>();
		if(productOptional.isPresent()) {
			response.setServiceData(mapProductList(productOptional.get()));
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}
	
	
	public ProductResponseDto getProductByCategory(String category, int pageIndex, int elementsPerPage,
			String ordreBy) {
		Optional<List<Product>> productOptional = productRepo.findByCategory(category,
				PageRequest.of(pageIndex, elementsPerPage,
						ordreBy.equals("asc") ? Sort.by("productName").ascending() 
								: Sort.by("productName").descending()));
		ProductResponseDto response = new ProductResponseDto();
		List<ProductDto> serviceData = new ArrayList<>();
		if(productOptional.isPresent()) {
			response.setServiceData(mapProductList(productOptional.get()));
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}

	
	public ProductResponseDto getAllProducts() {
		List<Product> product = productRepo.findAll();
		ProductResponseDto response = new ProductResponseDto();
		List<ProductDto> serviceData = new ArrayList<>();
		if(!product.isEmpty()) {
			response.setServiceData(mapProductList(product));
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}

	
	public ProductResponseDto getAllProducts(int pageIndex, int elementsPerPage, String orderBy) {
		Page<Product> productPage = productRepo.findAll(PageRequest.of(pageIndex, elementsPerPage,
				orderBy.equals("asc") ? Sort.by("productName").ascending() 
						: Sort.by("productName").descending()));
		ProductResponseDto response = new ProductResponseDto();
		List<ProductDto> serviceData = new ArrayList<>();
		if(!productPage.isEmpty()) {
			response.setServiceData(mapProductList(productPage.getContent()));
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}

	
	public ProductResponseDto getAllProducts(int pageIndex, int elementsPerPage) {
		Page<Product> productPage = productRepo.findAll(PageRequest.of(pageIndex, elementsPerPage));
		ProductResponseDto response = new ProductResponseDto();
		List<ProductDto> serviceData = new ArrayList<>();
		if(!productPage.isEmpty()) {
			response.setServiceData(mapProductList(productPage.getContent()));
			response.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			response.setErrorCode(Constants.success);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.success));
		}else {
			response.setServiceData(serviceData);
			response.setResponseCode(Constants.VALIDATION_ERROR_RESPONSE_CODE);
			response.setErrorCode(Constants.NO_REC_FOUND);
			response.setErrorDesc(localResolver.getErrorMessage(Constants.NO_REC_FOUND));
		}
		return response;
	}
	private ProductDto mapProduct(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(product.productId);
		productDto.setCategory(product.category);
		productDto.setImported(product.imported);
		productDto.setPrice(product.price);
		productDto.setProductName(product.productName);
		productDto.setProductType(product.productType);
		productDto.setStocks(product.stocks);
		return productDto;
	}
	
	private List<ProductDto> mapProductList(List<Product> productList) {
		List<ProductDto> productDtoList = new ArrayList<>();
		for(Product product: productList) {
			productDtoList.add(mapProduct(product));
		}
		return productDtoList;
	}
}
