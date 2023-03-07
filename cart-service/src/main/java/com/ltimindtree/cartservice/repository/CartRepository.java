package com.ltimindtree.cartservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ltimindtree.cartservice.entity.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query("from Cart c where c.userId = :userId")
	Optional<Cart> findbyUserId(long userId);

}
