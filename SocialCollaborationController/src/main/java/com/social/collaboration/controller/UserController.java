package com.social.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.collaboration.dao.UserDAO;
import com.social.collaboration.model.User;
import com.social.collaboration.service.UserService;

@RestController
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private User user;

	HttpSession httpSession;

	// Add new user
	// http://localhost:8085/SocialCollaborationController/add-user
	@PostMapping(value = "/add_user") // DONE
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user.setStatus('N');
		user.setIsOnline('N');
		user.setRole("User");

		// add more extra condition ************* To be checked later ***************
		// if user already exist
		
		/* if (userService.getUserByUserName(user.getUsername()) != null) {
		 System.out.println("----------------------------else--------------------");
		  
		 user.setErrorCode("100"); user.setErrorMessage("user already exist"); return
		 new ResponseEntity<User>(user, HttpStatus.CONFLICT); }*/
		 
		//else {
		System.out.println("----------------------------else--------------------");
		boolean value = userService.saveUser(user);
		if (value == true) {
			user.setErrorCode("200");
			user.setErrorMessage("User added Successfully");
		} else {
			user.setErrorCode("100");
			user.setErrorMessage("Add User Failed");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// Update a particular user Chec also. More details are there  -----Very important
	// http://localhost:8085/SocialCollaborationController/update_user
	@PutMapping(value = "/update_user")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		// Check whether user exixt or not
		if(userService.getUserByUserName(user.getUsername()) == null)
		{
			//System.out.println("----------------------------else--------------------");
			user.setErrorCode("100");
			user.setErrorMessage("User " + user.getUsername() + " does not exist");
			return new ResponseEntity<User>(user , HttpStatus.NOT_FOUND);
		}
		
		//if User exist
		if(userService.updateUser(user))
		{
			user.setErrorCode("200");
			user.setErrorMessage("User successfully updated");
			return new ResponseEntity<User>(user , HttpStatus.OK);
		}
		
		user.setErrorCode("100");
		user.setErrorMessage("Could not update user, Try after some time");
		return new ResponseEntity<User>(user , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//SHARHUKH SOURCE CODE TO BE USED OR CHECK LATER
	/*@PutMapping("/update_user")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		if(user != null)
		{
			boolean value = userService.saveUser(user);
			if (value == true) 
			{
				user.setErrorCode("200");
				user.setErrorMessage("User updated Successfully");
			} 
			else 
			{
				user.setErrorCode("100");
				user.setErrorMessage("Add User Failed");
				return null;
			}
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/
	
	//NOT EXECUTING TO BE CHECKED LATTER
	// http://localhost:8085/SocialCollaborationController/delete_user
	@DeleteMapping("/delete_user-{emailID}")
	public ResponseEntity<User> deleteUserEmail(@PathVariable("emailID") String emailID)
	{
		//Check whether user exit or not
		user = userService.getUserByEmailID(emailID);
		if(user == null)
		{
			//user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User " + emailID +" does not exixt");
			return new ResponseEntity<User>(user , HttpStatus.NOT_FOUND);
		}
		// check whether user applied for any job or not LATER
		
		if(user != null)
		{
			boolean value = userService.deleteUserByEmailID(emailID);
			if(value)
			{
				user.setErrorCode("200");
				user.setErrorMessage("User deleted successfuly");
			}
			else
			{
				user.setErrorCode("404");
				user.setErrorMessage("User is not deleted");
			}			
		}
		return new ResponseEntity<User>(user ,HttpStatus.OK);
	}
	
	// http://localhost:8085/SocialCollaborationController/deletes_user-{username}
	@DeleteMapping("/deletes_user-{username}")
	public ResponseEntity<User> deleteUserName(@PathVariable("username") String username)
	{
		//Check whether user exit or not
		user = userService.getUserByUserName(username);
		if(user == null)
		{
			//user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User " + username +" does not exixt");
			return new ResponseEntity<User>(user , HttpStatus.NOT_FOUND);
		}
		// check whether user applied for any job or not LATER
		
		if(user != null)
		{
			boolean value = userService.deleteUserByName(username);
			if(value)
			{
				user.setErrorCode("200");
				user.setErrorMessage("User deleted successfuly");
				return new ResponseEntity<User>(user ,HttpStatus.OK);
			}
			else
			{
				user.setErrorCode("404");
				user.setErrorMessage("User is not deleted");
				return new ResponseEntity<User>(user ,HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		return new ResponseEntity<User>(user ,HttpStatus.OK);
		
	}
	
	

	// Get All user
	// http://localhost:8085/SocialCollaborationController/getAllUserList
	@GetMapping("/get_all_userList")
	public ResponseEntity<List<User>> getUserList() {
		List<User> userList = userService.getAllUser();
		if (userList.isEmpty()) {
			user.setErrorCode("100");
			user.setErrorMessage("Users not available");
		} else {
			// DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy"); To be checked
			// later
			for (User user : userList) {
				user.setErrorCode("200");
				user.setErrorMessage("users list displayed successfully");
			}
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);

	}

	// Get by user name
	// http://localhost:8085/SocialCollaborationController/getUser-{username}
	@GetMapping("/getUser-{username}")
	public ResponseEntity<User> getUserName(@PathVariable("username") String username )
	{
		user = userService.getUserByUserName(username);
		if(user == null)
		{
			//user = new User();
			user.setErrorCode("100");
			user.setErrorMessage("User "+ username + "is not found");
		}
		
		user.setErrorCode("200");
		user.setErrorMessage("User "+ username + " is found");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//Get user by emailID  NOT WORKING TO BE CHECKED
	//http://localhost:8085/SocialCollaborationController/getUsers-{emailID}
	@GetMapping("/getUsers-{emailID}")
	public ResponseEntity<User> getUserEmailID(@PathVariable("emailID") String emailID )
	{
		user = userService.getUserByEmailID(emailID);
		if(user == null)
		{
			user = new User();
			user.setErrorCode("100");
			user.setErrorMessage("User "+ emailID + "is not found");
		}
		
		user.setErrorCode("200");
		user.setErrorMessage("User "+ emailID + " is found");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	//http://localhost:8085/SocialCollaborationController/user/login
	@PostMapping("/user/login")
	public ResponseEntity<User> validateCredential(@RequestBody User user)
	{
		if(userService.userValidation(user.getUsername(), user.getPassword()) == null)
		{
			user.setErrorCode("100");
			user.setErrorMessage("Invalide user");
			return new ResponseEntity<User>(user  , HttpStatus.UNAUTHORIZED);
		}
		else {
			user.setErrorCode("200");
			user.setErrorMessage("Valid user");
			return new ResponseEntity<User>(user , HttpStatus.OK);
		}
	}

}
