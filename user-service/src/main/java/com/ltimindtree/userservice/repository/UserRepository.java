package com.ltimindtree.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
