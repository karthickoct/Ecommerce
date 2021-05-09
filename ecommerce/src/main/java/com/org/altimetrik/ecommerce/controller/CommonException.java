package com.org.altimetrik.ecommerce.controller;

public class CommonException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private int responseCode;
	private String errorCode;
	private String errorDesc;
	
	public CommonException(int responseCode, String errorCode, String errorDesc) {
		super();
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
}