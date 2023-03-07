package com.ltimindtree.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ltimindtree.userservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("select a from Address a where a.user.userId = :userID")
	List<Address> findByUserId(long userID);
	
}
