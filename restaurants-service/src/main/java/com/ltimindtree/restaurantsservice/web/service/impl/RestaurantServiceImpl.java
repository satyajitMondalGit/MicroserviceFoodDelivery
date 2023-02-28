package com.ltimindtree.restaurantsservice.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltimindtree.restaurantsservice.entity.FoodItem;
import com.ltimindtree.restaurantsservice.entity.Restaurant;
import com.ltimindtree.restaurantsservice.exception.RestauranNotFoundException;
import com.ltimindtree.restaurantsservice.repository.FoodItemRepository;
import com.ltimindtree.restaurantsservice.repository.RestaurantRepository;
import com.ltimindtree.restaurantsservice.web.dto.FoodItemDto;
import com.ltimindtree.restaurantsservice.web.dto.MenuDTO;
import com.ltimindtree.restaurantsservice.web.dto.RestaurantDTO;
import com.ltimindtree.restaurantsservice.web.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	
	@Autowired
	private RestaurantRepository restaurantRepo;
	
	@Autowired
	private FoodItemRepository foodItemRepository;

	@Override
	public RestaurantDTO getResturantByName(String name) {
		Restaurant restaurent = restaurantRepo.findByRestaurantName(name).orElseThrow(()->new RestauranNotFoundException("No Restaurant found with this name"));
		return RestaurantDTO.builder()
				.restaurantId(restaurent.getRestaurantId())
				.restaurantName(restaurent.getRestaurantName())
				.address(restaurent.getAddress())
				.manu(MenuDTO.builder()
						.items(restaurent.getItems().stream().map(i -> FoodItemDto.builder()
								.itemId(i.getItemId())
								.itemName(i.getItemName())
								.category(i.getCategory())
								.cuisine(i.getCuisine())
								.price(i.getPrice())
								.build()).collect(Collectors.toList()))
						.build())
				.build();
	}

	@Override
	public List<RestaurantDTO> getResturantByLocation(String location) {
		List<Restaurant> restaurentList = restaurantRepo.findByRestaurantLocation(location);
		
		return restaurentList.stream().map(restaurent -> RestaurantDTO.builder()
				.restaurantId(restaurent.getRestaurantId())
				.restaurantName(restaurent.getRestaurantName())
				.address(restaurent.getAddress())
				.manu(MenuDTO.builder()
						.items(restaurent.getItems().stream().map(i -> FoodItemDto.builder()
								.itemId(i.getItemId())
								.itemName(i.getItemName())
								.category(i.getCategory())
								.cuisine(i.getCuisine())
								.price(i.getPrice())
								.build()).collect(Collectors.toList()))
						.build())
				.build()).collect(Collectors.toList());
	}

	@Override
	public List<RestaurantDTO> getResturantByCuisine(String cuisine) {
		List<Restaurant> restaurentList = restaurantRepo.findByRestaurantCuisine(cuisine);
		return restaurentList.stream().map(restaurent -> RestaurantDTO.builder()
				.restaurantId(restaurent.getRestaurantId())
				.restaurantName(restaurent.getRestaurantName())
				.address(restaurent.getAddress())
				.manu(MenuDTO.builder()
						.items(restaurent.getItems().stream().map(i -> FoodItemDto.builder()
								.itemId(i.getItemId())
								.itemName(i.getItemName())
								.category(i.getCategory())
								.cuisine(i.getCuisine())
								.price(i.getPrice())
								.build()).collect(Collectors.toList()))
						.build())
				.build()).collect(Collectors.toList());
	}

	@Override
	public List<RestaurantDTO> getResturantBudget(int budget) {
		List<Restaurant> restaurentList = restaurantRepo.findByBudget(budget);
		return restaurentList.stream().map(restaurent -> RestaurantDTO.builder()
				.restaurantId(restaurent.getRestaurantId())
				.restaurantName(restaurent.getRestaurantName())
				.address(restaurent.getAddress())
				.manu(MenuDTO.builder()
						.items(restaurent.getItems().stream().map(i -> FoodItemDto.builder()
								.itemId(i.getItemId())
								.itemName(i.getItemName())
								.category(i.getCategory())
								.cuisine(i.getCuisine())
								.price(i.getPrice())
								.build()).collect(Collectors.toList()))
						.build())
				.build()).collect(Collectors.toList());
	}

	@Override
	public MenuDTO getResturantMenubById(long id) {
		List<FoodItem>foodItemList = foodItemRepository.findByResturentId(id);
		return MenuDTO.builder()
				.items(foodItemList.stream().map(i -> FoodItemDto.builder()
						.itemId(i.getItemId())
						.itemName(i.getItemName())
						.category(i.getCategory())
						.cuisine(i.getCuisine())
						.price(i.getPrice())
						.build()).collect(Collectors.toList()))
				.build();
	}

	@Override
	public Restaurant addRestaurand(Restaurant restaurent) {
		return restaurantRepo.save(restaurent);
	}

}
