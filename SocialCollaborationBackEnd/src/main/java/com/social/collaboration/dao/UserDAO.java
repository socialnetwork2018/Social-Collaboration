package com.social.collaboration.dao;

import java.util.List;

import com.social.collaboration.model.User;

public interface UserDAO {

	// Method to save user
	public boolean saveUser(User user);

	// Method to update user
	public boolean updateUser(User user);
	
	//Fetch all the users
	public List <User> getAllUser();

	// Fetch user based on email id
	public User getUserByEmailID(String emailID);

	// Fetch user based on email username
	public User getUserByUserName(String username);

	// Method to delete user by email ID
	public boolean deleteUserByEmailID(String emailID);
	
	// Method to delete user by username
	public boolean deleteUserByName(String username);

	// Login method
	public User userValidation(String emailID, String password);

	// Add more methods

}
