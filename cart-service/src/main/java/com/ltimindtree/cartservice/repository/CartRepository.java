package com.ltimindtree.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.cartservice.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
