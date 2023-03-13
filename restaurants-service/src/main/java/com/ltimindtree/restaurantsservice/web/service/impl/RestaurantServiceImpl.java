package com.ltimindtree.restaurantsservice.web.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Functions;
import com.ltimindtree.restaurantsservice.constants.Constants;
import com.ltimindtree.restaurantsservice.entity.FoodItem;
import com.ltimindtree.restaurantsservice.entity.Restaurant;
import com.ltimindtree.restaurantsservice.exception.RestauranNotFoundException;
import com.ltimindtree.restaurantsservice.repository.FoodItemRepository;
import com.ltimindtree.restaurantsservice.repository.RestaurantRepository;
import com.ltimindtree.restaurantsservice.web.dto.FoodDTO;
import com.ltimindtree.restaurantsservice.web.dto.FoodItemDTO;
import com.ltimindtree.restaurantsservice.web.dto.MenuDTO;
import com.ltimindtree.restaurantsservice.web.dto.OrderProcessingStatusDTO;
import com.ltimindtree.restaurantsservice.web.dto.RestaurantDTO;
import com.ltimindtree.restaurantsservice.web.dto.RestaureantMsgDTO;
import com.ltimindtree.restaurantsservice.web.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Autowired
	private FoodItemRepository foodItemRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public RestaurantDTO getResturantByName(String name) {
		Restaurant restaurent = restaurantRepo.findByRestaurantName(name.trim())
				.orElseThrow(() -> new RestauranNotFoundException("No Restaurant found with this name"));
		return RestaurantDTO.builder().restaurantId(restaurent.getRestaurantId())
				.restaurantName(restaurent.getRestaurantName()).address(restaurent.getAddress())
				.manu(MenuDTO.builder()
						.items(restaurent.getItems().stream()
								.map(i -> FoodDTO.builder().itemId(i.getItemId()).itemName(i.getItemName())
										.category(i.getCategory()).cuisine(i.getCuisine()).price(i.getPrice()).build())
								.collect(Collectors.toList()))
						.build())
				.budget(restaurent.getBudget()).build();
	}

	@Override
	public List<RestaurantDTO> getResturantByLocation(String location) {
		List<Restaurant> restaurentList = restaurantRepo.findByRestaurantLocation(location.trim());

		return restaurentList.stream()
				.map(restaurent -> RestaurantDTO.builder().restaurantId(restaurent.getRestaurantId())
						.restaurantName(restaurent.getRestaurantName()).address(restaurent.getAddress())
						.manu(MenuDTO.builder().items(restaurent.getItems().stream()
								.map(i -> FoodDTO.builder().itemId(i.getItemId()).itemName(i.getItemName())
										.category(i.getCategory()).cuisine(i.getCuisine()).price(i.getPrice()).build())
								.collect(Collectors.toList())).build())
						.budget(restaurent.getBudget()).build())
				.collect(Collectors.toList());

	}

	@Override
	public List<RestaurantDTO> getResturantByCuisine(String cuisine) {
		List<Restaurant> restaurentList = restaurantRepo.findByRestaurantCuisine(cuisine.trim());
		return restaurentList.stream()
				.map(restaurent -> RestaurantDTO.builder().restaurantId(restaurent.getRestaurantId())
						.restaurantName(restaurent.getRestaurantName()).address(restaurent.getAddress())
						.manu(MenuDTO.builder().items(restaurent.getItems().stream()
								.map(i -> FoodDTO.builder().itemId(i.getItemId()).itemName(i.getItemName())
										.category(i.getCategory()).cuisine(i.getCuisine()).price(i.getPrice()).build())
								.collect(Collectors.toList())).build())
						.budget(restaurent.getBudget()).build())
				.collect(Collectors.toList());

	}

	@Override
	public List<RestaurantDTO> getResturantBudget(int budget) {
		List<Restaurant> restaurentList = restaurantRepo.findByBudget(budget);
		return restaurentList.stream()
				.map(restaurent -> RestaurantDTO.builder().restaurantId(restaurent.getRestaurantId())
						.restaurantName(restaurent.getRestaurantName()).address(restaurent.getAddress())
						.manu(MenuDTO.builder().items(restaurent.getItems().stream()
								.map(i -> FoodDTO.builder().itemId(i.getItemId()).itemName(i.getItemName())
										.category(i.getCategory()).cuisine(i.getCuisine()).price(i.getPrice()).build())
								.collect(Collectors.toList())).build())
						.budget(restaurent.getBudget()).build())
				.collect(Collectors.toList());

	}

	@Override
	public MenuDTO getResturantMenubById(long id) {
		List<FoodItem> foodItemList = foodItemRepository.findByResturentId(id);
		return MenuDTO.builder()
				.items(foodItemList.stream()
						.map(i -> FoodDTO.builder().itemId(i.getItemId()).itemName(i.getItemName())
								.category(i.getCategory()).cuisine(i.getCuisine()).price(i.getPrice()).build())
						.collect(Collectors.toList()))
				.build();

	}

	@Override
	public Restaurant addRestaurand(Restaurant restaurent) {

		List<FoodItem> footItemList = restaurent.getItems();

		Restaurant res = restaurantRepo.save(restaurent);

		List<FoodItem> footItemList2 = footItemList.stream().map(f -> {
			f.setRestaurant(res);
			return foodItemRepository.save(f);
		}).collect(Collectors.toList());

		res.setItems(footItemList2);

		return res;
	}

	@Override
	public void processTheOrderedFood(RestaureantMsgDTO restaurantDto) {
		List<FoodItem> foodItemList = foodItemRepository.findByResturentId(restaurantDto.getResturentId());
		Map<Integer, FoodItemDTO> itemMap = restaurantDto.getFootItemDTO().stream()
				.collect(Collectors.toMap(FoodItemDTO::getItemId, Function.identity()));

		List<FoodItem> fItemList = foodItemList.stream().filter(f -> itemMap.containsKey(f.getItemId())).map(f -> {
			FoodItemDTO fDto = itemMap.get(f.getItemId());
			int qty = f.getQuentity() - fDto.getQuentity();
			f.setQuentity(qty);
			return f;
		}).map(f -> foodItemRepository.save(f)).collect(Collectors.toList());

		updateStatusToOrder(OrderProcessingStatusDTO.builder().orderId(restaurantDto.getOrderId()).status("onTheWay").build());
		
	}

	private void updateStatusToOrder(OrderProcessingStatusDTO orderProcessingStatusDTO) {
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		rabbitTemplate.convertAndSend(Constants.RESTAURANTS_SERVICE_EXCHANGE, Constants.RESTAURANTS_ORDER_ROUTING_KEY, orderProcessingStatusDTO);
		
	}

}
