package com.siddhant.usermicroservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhant.usermicroservice.entity.User;
import com.siddhant.usermicroservice.repository.UserRepository;
import com.siddhant.usermicroservice.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userrepo;
	
	
	
	@PostMapping("/saveuser")
	public ResponseEntity <User> saveUser (@RequestBody User user) {
		User user1= userServiceImpl.saveUser(user);
		return new ResponseEntity<User>(user1,HttpStatus.CREATED);
	}	
	
	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> userlist = userServiceImpl.getAllUsers();
		return new ResponseEntity<List<User>>(userlist,HttpStatus.OK);
		
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<User>getUser(@PathVariable String id) {
		User getoneuser= userServiceImpl.getUser(id);
		return new ResponseEntity<User>(getoneuser,HttpStatus.OK);
	}
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<User> updateuser (@PathVariable String id ,@RequestBody User user){
		User updateduserinfo = userServiceImpl.updateUser(id, user);
		return new ResponseEntity<User>(updateduserinfo,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<Map<String,String>> deleteUser (@PathVariable String id){
	
		userrepo.deleteById(id);
		String deleteuser =	"User Detail Deleted Successfully!!!";
		
		Map map = new HashMap<>();
		map.put("Message", deleteuser);
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	
	}
	
	
}
