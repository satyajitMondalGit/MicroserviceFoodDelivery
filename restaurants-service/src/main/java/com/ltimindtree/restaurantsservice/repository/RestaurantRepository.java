package com.ltimindtree.restaurantsservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.restaurantsservice.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	Optional<Restaurant> findByRestaurantName(String name);

	List<Restaurant> findByRestaurantLocation(String location);

	List<Restaurant> findByRestaurantCuisine(String cuisine);

	List<Restaurant> findByBudget(int budget);

}
