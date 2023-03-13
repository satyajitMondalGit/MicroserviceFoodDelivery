package com.ltimindtree.orderservice.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltimindtree.orderservice.entity.FoodItem;
import com.ltimindtree.orderservice.entity.Order;
import com.ltimindtree.orderservice.entity.OrderStatus;
import com.ltimindtree.orderservice.exception.OrderNotFoundException;
import com.ltimindtree.orderservice.repository.OrderRepository;
import com.ltimindtree.orderservice.web.dto.AddressDTO;
import com.ltimindtree.orderservice.web.dto.CartDTO;
import com.ltimindtree.orderservice.web.dto.DeliveryDTO;
import com.ltimindtree.orderservice.web.dto.PaymentProcessingDTO;
import com.ltimindtree.orderservice.web.dto.RequestOrderDTO;
import com.ltimindtree.orderservice.web.dto.ResponseCartDTO;
import com.ltimindtree.orderservice.web.dto.ResponseOrderDTO;
import com.ltimindtree.orderservice.web.dto.RestaureantMsgDTO;
import com.ltimindtree.orderservice.web.service.CartService;
import com.ltimindtree.orderservice.web.service.DeliveryService;
import com.ltimindtree.orderservice.web.service.OrderService;
import com.ltimindtree.orderservice.web.service.PaymentService;
import com.ltimindtree.orderservice.web.service.RestaurantService;
import com.ltimindtree.orderservice.web.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepo;

	private UserService userService;

	private DeliveryService deliveryService;

	private PaymentService paymentService;

	private RestaurantService resturantService;

	private CartService cartService;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepo, UserService userService, DeliveryService deliveryService,
			PaymentService paymentService, RestaurantService resturantService, CartService cartService) {
		super();
		this.orderRepo = orderRepo;
		this.userService = userService;
		this.deliveryService = deliveryService;
		this.paymentService = paymentService;
		this.resturantService = resturantService;
		this.cartService = cartService;
	}

	@Override
	public ResponseOrderDTO placeOrder(RequestOrderDTO requestOrderDTO) {
		
		log.info(" Before User service requestOrderDTO "+requestOrderDTO);

		AddressDTO addsDto = userService.getAddressOfTheUser(requestOrderDTO.getUserId(),
				requestOrderDTO.getAddressId());

		log.info(" After User service addsDto "+addsDto);
		
		ResponseCartDTO cartDto = cartService.getCartDetails(requestOrderDTO.getUserId());

		log.info(" After cart service cartDto "+cartDto);
		
		String status = paymentService.getpaymentStatus(PaymentProcessingDTO.builder().amount(cartDto.getTotalPrice())
				.cardDetails(requestOrderDTO.getCardDatils()).build());

		log.info(" After pyment service status "+status);
		
		Order order = updateOrderDetails(cartDto, status);

//		RestaureantDTO restaurantDto = RestaureantDTO.builder().OrderId(order.getOrderId())
//				.ResturentId(order.getRestaurantId()).UserId(order.getUserId()).addressDTO(addsDto)
//				.footItemDTO(cartDto.getFooditems()).build();

		resturantService.updateToResturent(RestaureantMsgDTO.builder().OrderId(order.getOrderId())
				.ResturentId(order.getRestaurantId()).UserId(order.getUserId()).addressDTO(addsDto)
				.footItemDTO(cartDto.getFooditems()).build());

//		DeliveryDTO deliveryDto = DeliveryDTO.builder().OrderId(order.getOrderId()).ResturentId(order.getRestaurantId())
//				.UserId(order.getUserId()).addressDTO(addsDto).build();
		deliveryService.pickDeliveryPerson(DeliveryDTO.builder().OrderId(order.getOrderId()).ResturentId(order.getRestaurantId())
				.UserId(order.getUserId()).addressDTO(addsDto).build());

		log.info(" before cart service delete service cartDto "+cartDto);
		cartService.removeItemsFromCartAfterOrderPlaced(CartDTO.builder().userId(cartDto.getUserId()).resturantId(cartDto.getRestaurantId()).foodItem(cartDto.getFooditems()).build());
		log.info(" after cart service delete service cartDto "+cartDto);
		return ResponseOrderDTO.builder().message("Your Current Order status is -- " + order.getStatus()).order(order)
				.build();
	}

	private Order updateOrderDetails(ResponseCartDTO cartDto, String status) {

		OrderStatus orderStatus = OrderStatus.pending;
		if (status.equalsIgnoreCase("Success")) {
			orderStatus = OrderStatus.placed;
		} else {
			orderStatus = OrderStatus.failed;
		}

		Order order = orderRepo
				.save(Order.builder().orderId(0).userId(cartDto.getUserId()).restaurantId(cartDto.getRestaurantId())
						.foodItems(null).totalPrice(cartDto.getTotalPrice()).status(orderStatus).build());

		List<FoodItem> foods = cartDto.getFooditems().stream()
				.map(f -> FoodItem.builder().itemId(f.getItemId()).itemName(f.getItemName()).category(f.getCategory())
						.cuisine(f.getCuisine()).price(f.getPrice()).quentity(f.getQuentity()).order(order).build())
				.collect(Collectors.toList());
		order.setFoodItems(foods);
		return order;
	}

	@Override
	public ResponseOrderDTO getOrderDetails(long orderId) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Orderis not found with Id" + orderId));
		return ResponseOrderDTO.builder().message("Your Order's currently in " + order.getStatus()).order(order)
				.build();
	}

	@Override
	public void uppateStatus(long orderId, String status) {
		Order ord = orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("order not found on this Id "+orderId));
		if(status.equalsIgnoreCase("Delivered")) {
			ord.setStatus(OrderStatus.delivered);
		}else if(status.equalsIgnoreCase("OnTheWay")) {
			ord.setStatus(OrderStatus.onTheWay);
		}else {
			ord.setStatus(OrderStatus.failed);
		}
		orderRepo.save(ord);
	}

}
