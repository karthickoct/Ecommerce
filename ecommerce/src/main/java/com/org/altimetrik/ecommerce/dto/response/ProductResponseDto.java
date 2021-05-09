package com.org.altimetrik.ecommerce.dto.response;

import java.util.List;

import com.org.altimetrik.ecommerce.dto.ProductDto;

public class ProductResponseDto extends CommonResponse {
	private List<ProductDto> serviceData;

	public List<ProductDto> getServiceData() {
		return serviceData;
	}

	public void setServiceData(List<ProductDto> serviceData) {
		this.serviceData = serviceData;
	}
}