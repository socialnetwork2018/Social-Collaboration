package com.social.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.social.collaboration.dao.UserDAO;
import com.social.collaboration.model.User;
import com.social.collaboration.service.UserService;

@Service
@Transactional
public class UserserviceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;


	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.saveUser(user);
	}
	
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(user);
	}
	
	public List<User> getAllUser()
	{
		return userDAO.getAllUser();
	}


	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userDAO.getUserByUserName(username);
	}

	public User getUserByEmailID(String emailID) {
		// TODO Auto-generated method stub
		return userDAO.getUserByEmailID(emailID);
	}

	public boolean deleteUser(String emailID) {
		// TODO Auto-generated method stub
		return userDAO.deleteUserByEmailID(emailID);
	}

	public boolean deleteUserByEmailID(String emailID) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteUserByName(String username) {
		// TODO Auto-generated method stub
		return userDAO.deleteUserByName(username);
	}

	public User userValidation(String emailID, String password) {
		// TODO Auto-generated method stub
		return userDAO.userValidation(emailID, password);
	}


}
