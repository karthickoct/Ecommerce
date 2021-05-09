package com.org.altimetrik.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.altimetrik.ecommerce.dto.CartRequestDto;
import com.org.altimetrik.ecommerce.dto.CartResponseDto;
import com.org.altimetrik.ecommerce.model.CartDetails;
import com.org.altimetrik.ecommerce.service.CartService;


@RestController
@RequestMapping(value = "/api")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping(value = "/cart/add")
	public CartResponseDto addToCart(@RequestBody CartRequestDto request) throws Exception {
		return cartService.addToCart(request);
	}
	
	@GetMapping(value = "/cart/getCartDetails/{userName}")
	public List<CartDetails> getCartDetails(@PathVariable String userName) throws Exception {
		return cartService.findByUserName(userName);
	}
	
}
