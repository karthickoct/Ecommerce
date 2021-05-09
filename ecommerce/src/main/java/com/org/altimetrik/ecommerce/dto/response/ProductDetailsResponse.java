package com.org.altimetrik.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsResponse {
	
	public Long PurchaseId;
	public String errorCode;
	public String errorDesc;
	
}
