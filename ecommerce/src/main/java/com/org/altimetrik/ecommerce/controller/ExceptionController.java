package com.org.altimetrik.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.org.altimetrik.ecommerce.dto.response.CommonResponse;
import com.org.altimetrik.ecommerce.service.MessageByLocaleService;
import com.org.altimetrik.ecommerce.util.Constants;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{
	Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	@Autowired
	MessageByLocaleService localResolver;
	
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<Object> genericException(CommonException res) {
		CommonResponse response = new CommonResponse(res.getResponseCode() != 0 ? res.getResponseCode() : -1, 
													res.getErrorCode() != null ? res.getErrorCode() : Constants.internalErrorCode, 
													res.getErrorCode() != null ? localResolver.getErrorMessage(res.getErrorCode()) : localResolver.getErrorMessage(Constants.internalErrorCode));
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> genericException(Exception e) {
		logger.error("Error Occured : {}",e);
		e.printStackTrace();
		
		CommonResponse response = new CommonResponse(-1,Constants.internalErrorCode,localResolver.getErrorMessage(Constants.internalErrorCode)) ;
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
