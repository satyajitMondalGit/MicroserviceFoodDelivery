package com.ltimindtree.orderservice.web.service;

import com.ltimindtree.orderservice.web.dto.CartDTO;
import com.ltimindtree.orderservice.web.dto.ResponseCartDTO;

public interface CartService {

	ResponseCartDTO getCartDetails(long userId);

	void removeItemsFromCartAfterOrderPlaced(CartDTO cartDto);

}
