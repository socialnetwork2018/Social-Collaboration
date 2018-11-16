package com.social.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.social.collaboration.dao.ParentDAO;
import com.social.collaboration.model.Parent;
import com.social.collaboration.service.ParentService;

@Transactional
@Service
public class ParentServiceImpl implements ParentService{
	
	@Autowired
	ParentDAO parentDAO;

	public boolean saveParent(Parent parent) {
		// TODO Auto-generated method stub
		return parentDAO.saveParent(parent);
	}

	public boolean updateParent(Parent parent) {
		// TODO Auto-generated method stub
		return parentDAO.updateParent(parent);
	}

	public List<Parent> getAllParent() {
		// TODO Auto-generated method stub
		return parentDAO.getAllParent();
	}

	public Parent getParentByEmailID(String emailID) {
		// TODO Auto-generated method stub
		return parentDAO.getParentByEmailID(emailID);
	}

	public Parent getParentByParentName(String parentname) {
		// TODO Auto-generated method stub
		return null;
	}

	public Parent getParentByID(int parentID) {
		// TODO Auto-generated method stub
		return parentDAO.getParentByID(parentID);
	}

	public boolean deleteParent(String emailID) {
		// TODO Auto-generated method stub
		return parentDAO.deleteParent(emailID);
	}

	public boolean deleteParentByID(int parentID) {
		// TODO Auto-generated method stub
		return parentDAO.deleteParentByID(parentID);
	}

	public Parent parentValidation(int parentID, String password) {
		// TODO Auto-generated method stub
		return parentDAO.parentValidation(parentID, password);
	}

}
