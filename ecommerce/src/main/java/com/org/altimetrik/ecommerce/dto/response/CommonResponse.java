package com.org.altimetrik.ecommerce.dto.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.altimetrik.ecommerce.service.MessageByLocaleService;
import com.org.altimetrik.ecommerce.util.Constants;

@Component
public class CommonResponse{
	
	@Autowired
	MessageByLocaleService localResolver;
	
	private int responseCode;
	private String errorCode;
	private String errorDesc;

	public CommonResponse() {
		
	}
	
	public CommonResponse(int responseCode,String errorCode,String errorDesc) {
		this.responseCode = responseCode;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	public CommonResponse defaultSuccess() {
		return new CommonResponse(0, Constants.success, localResolver.getErrorMessage(Constants.success));
	}
	
	public CommonResponse defaultError() {
		return new CommonResponse(-1, Constants.internalErrorCode, localResolver.getErrorMessage(Constants.internalErrorCode));
	}

	@Override
	public String toString() {
		return "CommonResponse [localResolver=" + localResolver + ", responseCode=" + responseCode + ", errorCode="
				+ errorCode + ", errorDesc=" + errorDesc + "]";
	}
}