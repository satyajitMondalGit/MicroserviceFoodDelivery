package com.ltimindtree.restaurantsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ltimindtree.restaurantsservice.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {

	@Query("select f from FoodItem f where f.restaurant.restaurantId = :id")
	List<FoodItem> findByResturentId(long id);


}
