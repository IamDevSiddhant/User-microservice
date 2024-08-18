package com.siddhant.usermicroservice.service;

import java.util.List;

import com.siddhant.usermicroservice.entity.User;

public interface UserService {

	
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId);
	
	User updateUser(String Id, User existinguser);

	//String deleteUser(String Id);
	
	
}
