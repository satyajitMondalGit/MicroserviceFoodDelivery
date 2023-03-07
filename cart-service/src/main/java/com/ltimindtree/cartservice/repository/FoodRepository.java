package com.ltimindtree.cartservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ltimindtree.cartservice.entity.FoodItem;

public interface FoodRepository extends JpaRepository<FoodItem, Long> {

	
	@Query("select f from FoodItem f where f.cart.userId = :userId")
	List<FoodItem> findbyUserId(long userId);
}
