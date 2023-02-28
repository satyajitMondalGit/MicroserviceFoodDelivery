package com.ltimindtree.restaurantsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.restaurantsservice.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {

	List<FoodItem> findByResturentId(long id);

}
