package com.ltimindtree.restaurantsservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ltimindtree.restaurantsservice.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	
	
	Optional<Restaurant> findByRestaurantName(String name);

	@Query("select r from Restaurant r join r.locationTag l where l = :location")
	List<Restaurant> findByRestaurantLocation(String location);

	@Query("select r from Restaurant r join r.cuisineTag c where c = :cuisine")
	List<Restaurant> findByRestaurantCuisine(String cuisine);

	@Query("select r from Restaurant r where r.budget >= :budget")
	List<Restaurant> findByBudget(int budget);

//	Optional<Restaurant> findByRestaurantName(String name);
//
//	List<Restaurant> findByRestaurantLocation(String location);
//
//	List<Restaurant> findByRestaurantCuisine(String cuisine);
//
//	List<Restaurant> findByBudget(int budget);

}
