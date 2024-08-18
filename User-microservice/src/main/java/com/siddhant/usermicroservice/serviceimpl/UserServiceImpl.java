package com.siddhant.usermicroservice.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siddhant.usermicroservice.entity.Hotel;
import com.siddhant.usermicroservice.entity.Rating;
import com.siddhant.usermicroservice.entity.User;
import com.siddhant.usermicroservice.exception.GlobalException;
import com.siddhant.usermicroservice.repository.UserRepository;
import com.siddhant.usermicroservice.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		String randomuserid = UUID.randomUUID().toString(); 
		user.setId(randomuserid);
		return userrepo.save(user); 
	}

	@Override
	public List<User> getAllUsers() {
		
		
		/*
		 * 
		 * to get feedback for the particular user id
		 * we will consume 
		 * http://localhost:8085/rating/getByUserId/c2c6428a-be0e-4013-b5ba-37a080836a8e
		 * from rating micro-service
		 * using rest template
		 * 
		 * */
		List<User> userList = userrepo.findAll();
		
		userList
		.stream()//this will form stream of single user object
		.map((user)->{
			//call rating api
			Rating[] ratingofusers = restTemplate.getForObject("http://RATING-MICROSERVICE/rating/getByUserId/" + user.getId(),
					Rating[].class);
			//CONVERT to list (from array to list)
			List<Rating> ratingList = Arrays.stream(ratingofusers).toList();
			
			//set Rating and hotel to every user
			
			List<Rating> ratings = ratingList.stream().map((rating)->{
				
				//call hotel api
				ResponseEntity<Hotel> response = restTemplate
						.getForEntity("http://HOTEL-MICROSERVICE/hotels/gethotel/" + rating.getHotelId(), Hotel.class);
				Hotel hotel = response.getBody();
				// sethotel in rating list
				rating.setHotel(hotel);
				
				return rating;
			}).collect(Collectors.toList());
			
			user.setRatingList(ratings);
			return user;
		})
		.collect(Collectors.toList());
		return userList;
	}

	@Override
	public User getUser(String userId) {
		/*
		 * 
		 * to get feedback for the particular user id
		 * we will consume 
		 * http://localhost:8085/rating/getByUserId/c2c6428a-be0e-4013-b5ba-37a080836a8e
		 * from rating micro-service
		 * using rest template
		 * 
		 * */
		User user = userrepo.findById(userId).orElseThrow(() -> new GlobalException("User Not Found on Server!!!"));

		Rating[] ratingofusers = restTemplate.getForObject("http://RATING-MICROSERVICE/rating/getByUserId/" + userId,
				Rating[].class);

		log.info("RestTemplate Object " + ratingofusers);

		List<Rating> ratings = Arrays.stream(ratingofusers).toList();

		List<Rating> ratinghotelList = ratings.stream().map((rating) -> {
			// call hotel api
			ResponseEntity<Hotel> response = restTemplate
					.getForEntity("http://HOTEL-MICROSERVICE/hotels/gethotel/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = response.getBody();
			// sethotel in rating list
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatingList(ratinghotelList);

		log.info("User Object with rating List " + user);

		return user;
}

	@Override
	public User updateUser(String Id,User updatedUser) {
		
		User existingUser = userrepo.findById(Id).orElseThrow(()->new GlobalException("User Not Found on Server for updating!!!"));
		
//		User updatedUser = new User();
		
		//existingUser.setId(updatedUser.getId());
		existingUser.setUserName(updatedUser.getUserName());
		existingUser.setUserEmail(updatedUser.getUserEmail());
		existingUser.setUserDescription(updatedUser.getUserDescription());
		
		return userrepo.save(existingUser);
	}

//	@Override
//	public String deleteUser(String Id) {
//		userrepo.deleteById(Id);
//		return "User Deleted!!!";
//	}

}
