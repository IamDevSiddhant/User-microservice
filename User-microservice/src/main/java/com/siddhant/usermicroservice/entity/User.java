package com.siddhant.usermicroservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;


@Entity
@Table(name="user_details")
@Data

public class User {

	@Id
	private String Id;
	private String userName;
	private String userEmail;
	private String userDescription;
	
	//Transient annotation is used to not store Rating in user_details table in microservices database
	
	@Transient
	private List<Rating> ratingList = new ArrayList<>();
	
	
	public User() {
		super();
	}



	public User(String id, String userName, String userEmail, String userDescription, List<Rating>ratingList) {
		super();
		Id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userDescription = userDescription;
		this.ratingList = ratingList;
	}



	public String getId() {
		return Id;
	}



	public void setId(String id) {
		Id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getUserDescription() {
		return userDescription;
	}



	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}



	public List<Rating> getRatingList() {
		return ratingList;
	}



	public void setRatingList(List<Rating> ratingList) {
		this.ratingList = ratingList;
	}
	
	
	
	
	
}
