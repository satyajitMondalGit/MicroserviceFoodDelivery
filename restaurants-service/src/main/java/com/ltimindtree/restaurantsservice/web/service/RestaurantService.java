package com.ltimindtree.restaurantsservice.web.service;

import java.util.List;

import com.ltimindtree.restaurantsservice.entity.Restaurant;
import com.ltimindtree.restaurantsservice.web.dto.MenuDTO;
import com.ltimindtree.restaurantsservice.web.dto.RestaurantDTO;



public interface RestaurantService {

	RestaurantDTO getResturantByName(String name);

	List<RestaurantDTO> getResturantByLocation(String location);

	List<RestaurantDTO> getResturantByCuisine(String cuisine);

	List<RestaurantDTO> getResturantBudget(int budget);

	MenuDTO getResturantMenubById(long id);

	Restaurant addRestaurand(Restaurant restaurent);
	

}
