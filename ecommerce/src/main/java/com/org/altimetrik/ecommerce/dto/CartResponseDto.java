package com.org.altimetrik.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {
	
	public Long cartId;
	public String errorCode;
	public String errorDesc;
	
}
