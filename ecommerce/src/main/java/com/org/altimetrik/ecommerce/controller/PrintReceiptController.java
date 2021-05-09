/**
 * 
 */
package com.org.altimetrik.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.altimetrik.ecommerce.service.PrintReceiptService;

/**
 * @author System
 *
 */
@RestController
@RequestMapping(value = "/api")
public class PrintReceiptController {

	@Autowired
	public PrintReceiptService printReceiptService;

	@GetMapping(value = "/receipt/{purchaseId}")
	public String printReceipt(@PathVariable("purchaseId") Long purchaseId) throws Exception {
		
		return  printReceiptService.printReceipt(purchaseId);
	}


}
