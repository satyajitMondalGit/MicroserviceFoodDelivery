package com.ltimindtree.cartservice.web.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.cartservice.web.dto.CartDTO;
import com.ltimindtree.cartservice.web.dto.ResponseCartDTO;
import com.ltimindtree.cartservice.web.service.CartService;

@RequestMapping("api/v1/cart/")
@RestController
public class CartController {

	private CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping("add")
	ResponseEntity<ResponseCartDTO> addItemInTheCart(@Valid @RequestBody CartDTO cartDto) {

		return new ResponseEntity<>(cartService.addItemInTheCart(cartDto), HttpStatus.ACCEPTED);
	}

	@PutMapping("update")
	ResponseEntity<ResponseCartDTO> updateItemOfTheCart(@Valid @RequestBody CartDTO cartDto) {

		return new ResponseEntity<>(cartService.updateItemOfTheCart(cartDto), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("delete")
	public void deleteItemFromTheCart(@Valid @RequestBody CartDTO cartDto) {

		cartService.deleteItemFromTheCart(cartDto);
		//return new ResponseEntity<>(, HttpStatus.OK);
	}

	@GetMapping("{userId}")
	ResponseEntity<ResponseCartDTO> getCartDetails(@PathVariable @Valid long userId) {

		return new ResponseEntity<>(cartService.getCartDetails(userId), HttpStatus.OK);
	}
}
