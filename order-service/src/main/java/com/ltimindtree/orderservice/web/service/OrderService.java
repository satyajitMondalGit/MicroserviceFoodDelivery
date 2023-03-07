package com.ltimindtree.orderservice.web.service;

import com.ltimindtree.orderservice.web.dto.RequestOrderDTO;
import com.ltimindtree.orderservice.web.dto.ResponseOrderDTO;

public interface OrderService {

	ResponseOrderDTO placeOrder(RequestOrderDTO requestOrderDTO);

	ResponseOrderDTO getOrderDetails(long orderId);

}
