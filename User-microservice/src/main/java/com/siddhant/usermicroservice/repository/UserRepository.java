package com.siddhant.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhant.usermicroservice.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	
	
}
