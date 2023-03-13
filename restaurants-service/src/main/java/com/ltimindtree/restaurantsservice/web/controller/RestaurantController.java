package com.ltimindtree.restaurantsservice.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.restaurantsservice.entity.Restaurant;
import com.ltimindtree.restaurantsservice.web.dto.MenuDTO;
import com.ltimindtree.restaurantsservice.web.dto.RestaurantDTO;
import com.ltimindtree.restaurantsservice.web.service.RestaurantService;

@RestController
@RequestMapping("api/v1/restaurant/")
public class RestaurantController {
	
	
	private RestaurantService restaurantService; 

	public RestaurantController(RestaurantService restaurantService) {
		super();
		this.restaurantService = restaurantService;
	}

	@GetMapping("name/{name}")
	ResponseEntity<RestaurantDTO> getResturantByName(@PathVariable String name){
		
		return new ResponseEntity<>(restaurantService.getResturantByName(name), HttpStatus.OK);
	}
	
	@GetMapping("location/{location}")
	ResponseEntity<List<RestaurantDTO>> getResturantByLocation(@PathVariable String location){
		
		return new ResponseEntity<>(restaurantService.getResturantByLocation(location), HttpStatus.OK);
	}
	
	@GetMapping("cuisine/{cuisine}")
	ResponseEntity<List<RestaurantDTO>> getResturantByCuisine(@PathVariable String cuisine){
		
		return new ResponseEntity<>(restaurantService.getResturantByCuisine(cuisine), HttpStatus.OK);
	}
	
	@GetMapping("budget/{budget}")
	ResponseEntity<List<RestaurantDTO>> getResturantBudget(@PathVariable int budget){
		
		return new ResponseEntity<>(restaurantService.getResturantBudget(budget), HttpStatus.OK);
	}
	
	@GetMapping("{id}/menu")
	ResponseEntity<MenuDTO> getResturantMenubById(@PathVariable long id){
		
		return new ResponseEntity<>(restaurantService.getResturantMenubById(id), HttpStatus.OK);
	}
	
	@PostMapping("add/")
	ResponseEntity<?> addRestaurand(@RequestBody Restaurant restaurent){
		
		return new ResponseEntity<>(restaurantService.addRestaurand(restaurent), HttpStatus.CREATED);
	}
	
}
