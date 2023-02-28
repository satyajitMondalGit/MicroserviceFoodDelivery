package com.ltimindtree.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.deliveryservice.entity.DeliveryPerson;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long>{

}
