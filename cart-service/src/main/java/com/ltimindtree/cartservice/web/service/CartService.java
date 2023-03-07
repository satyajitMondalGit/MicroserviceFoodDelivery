package com.ltimindtree.cartservice.web.service;

import com.ltimindtree.cartservice.web.dto.CartDTO;
import com.ltimindtree.cartservice.web.dto.ResponseCartDTO;

public interface CartService {

	ResponseCartDTO addItemInTheCart(CartDTO cartDto);

	ResponseCartDTO updateItemOfTheCart(CartDTO cartDto);

	String deleteItemFromTheCart(CartDTO cartDto);

	ResponseCartDTO getCartDetails(long userId);

}
