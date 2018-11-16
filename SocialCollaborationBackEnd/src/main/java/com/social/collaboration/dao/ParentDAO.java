package com.social.collaboration.dao;

import java.util.List;

import com.social.collaboration.model.Parent;

public interface ParentDAO {

	// Method to save parent
	public boolean saveParent(Parent parent);

	// Method to update parent
	public boolean updateParent(Parent parent);

	// Fetch all the parents
	public List<Parent> getAllParent();

	// Fetch parent based on email id
	public Parent getParentByEmailID(String emailID);

	// Fetch parent based on email parentname
	public Parent getParentByParentName(String parentname);

	// Fetch parent based on email parentname
	public Parent getParentByID(int parentID);

	// Method to delete parent by emailID
	public boolean deleteParent(String emailID);

	// Method to delete parent by ID
	public boolean deleteParentByID(int parentID);

	// Login method
	public Parent parentValidation(int parentID, String password);

	// Add more methods

}
