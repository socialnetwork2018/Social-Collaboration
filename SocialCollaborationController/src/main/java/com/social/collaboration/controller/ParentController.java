package com.social.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
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
import com.social.collaboration.dao.ParentDAO;
import com.social.collaboration.model.Parent;
import com.social.collaboration.model.User;
import com.social.collaboration.service.ParentService;

@RestController
public class ParentController {

	Logger logger = LoggerFactory.getLogger(ParentController.class);

	@Autowired
	ParentService parentService;

	@Autowired
	ParentDAO parentDAO;

	@Autowired
	Parent parent;

	@Autowired
	HttpSession httpSession;

	// Add new parent
	// http://localhost:8580/SocialCollaborationController/add/parent
	@PostMapping("/parent/add")
	public ResponseEntity<Parent> addNewParent(@RequestBody Parent parent) {
		// logger.info("saveParenty method starts");

		// Add more conditions ABBAS NIIT
		boolean value = parentService.saveParent(parent);
		if (value == true) {
			parent.setErrorCode("200");
			parent.setErrorMessage("Parent added successfuly");
		} else {
			parent.setErrorCode("100");
			parent.setErrorMessage("Fail to add Parent");
			return new ResponseEntity<Parent>(parent, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<Parent>(parent, HttpStatus.OK);
	}

	// Edit parent
	// http://localhost:8580/SocialCollaborationController/update/parent
	@PutMapping("parent/update")
	public ResponseEntity<Parent> updateParent(@RequestBody Parent parent) {
		// Add more conditions ABBAS NIIT

		// if parent does not exist
		/*
		 * if(parentService.getParentByParentName(parent.getParentname())==null)
		 * //NOT WORKING { parent.setErrorCode("404");
		 * parent.setErrorMessage("Parent " +parent.getParentname() + " not found");
		 * return new ResponseEntity<Parent>(parent, HttpStatus.NOT_FOUND); }
		 */

		if (parent != null) {
			boolean value = parentService.updateParent(parent);
			if (value == true) {
				parent.setErrorCode("200");
				parent.setErrorMessage("Parent updated Successfully");
			} else {
				parent.setErrorCode("100");
				parent.setErrorMessage("Add Parent Failed");
				return null;
			}
		}

		return new ResponseEntity<Parent>(parent, HttpStatus.OK);
	}

	// Delete parent by parent name
	// http://localhost:8085/SocialCollaborationController/parent/delete/parentname
	@DeleteMapping("delete_parent-{parentname}")
	public ResponseEntity<Parent> deleteParentName(@PathVariable("parentname") String parentname) {
		// Check whether parent exit or not
		// parent = parentService.getParentByParentName(parentname);
		/*
		 * if(parent == null) { //parent = new Parent(); parent.setErrorCode("404");
		 * parent.setErrorMessage("Parent " + parentname +" does not exixt"); return
		 * new ResponseEntity<Parent>(parent , HttpStatus.NOT_FOUND); }
		 */
		// check whether parent applied for any job or not LATER

		if (parent != null) {
			boolean value = parentService.deleteParent(parentname);
			if (value) {
				parent.setErrorCode("200");
				parent.setErrorMessage("Parent deleted successfuly");
				// return new ResponseEntity<Parent>(parent ,HttpStatus.OK);
			} else {
				parent.setErrorCode("404");
				parent.setErrorMessage("Parent is not deleted");
				return new ResponseEntity<Parent>(parent, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Parent>(parent, HttpStatus.OK);

	}

	// Get user by emailID NOT WORKING TO BE CHECKED
	// http://localhost:8085/SocialCollaborationController/getParent-{emailID}
	@GetMapping("/getParent-{emailID}")
	public ResponseEntity<Parent> getParentEmailID(@PathVariable("emailID") String emailID) {
		parent = parentService.getParentByEmailID(emailID);
		if (parent == null) {
			parent = new Parent();
			parent.setErrorCode("100");
			parent.setErrorMessage("Parent " + emailID + "is not found");
			return new ResponseEntity<Parent>(parent, HttpStatus.NOT_FOUND);
		} else {

			parent.setErrorCode("200");
			parent.setErrorMessage("User " + emailID + " is found");
			return new ResponseEntity<Parent>(parent, HttpStatus.OK);
		}
	}


	// http://localhost:8085/SocialCollaborationController/getparentid-{parentID}
	@GetMapping("/getparentid/{parentID}")
	public ResponseEntity<Parent> getParentbyid(@PathVariable("parentID") int parentID)
	{
		parent = parentService.getParentByID(parentID);
		if(parent  == null)
		{
			parent = new Parent();
			parent.setErrorCode("100");
			parent.setErrorMessage("Parent " + parentID + " is not found");
			return new ResponseEntity<Parent>(parent, HttpStatus.OK);
		}
		 else {

				parent.setErrorCode("200");
				parent.setErrorMessage("User " + parentID + " is found");
				return new ResponseEntity<Parent>(parent, HttpStatus.NOT_FOUND);
			}
	}
	
	// http://localhost:8085/SocialCollaborationController/getallparentlist
	@GetMapping("/getallparentlist")
	public ResponseEntity<List<Parent>>getAllParenttList()
	{
		List<Parent> parentList = parentService.getAllParent();
		if(parentList.isEmpty())
		{
			parent.setErrorCode("100");
			parent.setErrorMessage("Parent are not available");
		}
		else
		{
			//DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); LATER
			for(Parent parent : parentList)
			{
				parent.setErrorCode("200");
				parent.setErrorMessage("Parent found");
				/*if(user.getDob() != null)		TO BE CHECKED LATER
					user.setBirthdate(df.format(user.getDob()));*/
			}
		}
		return new ResponseEntity<List<Parent>>(parentList , HttpStatus.OK);
	}
	
	
		//http://localhost:8085/SocialCollaborationController/parent/delete/{username}
		@DeleteMapping("/parent/delete/{parentID}")
		public ResponseEntity<Parent> deleteparentid(@PathVariable("parentID") int parentID)
		{
			//Check whether user exit or not
			parent = parentService.getParentByID(parentID);
			if(parent == null)
			{
				//user = new User();
				parent.setErrorCode("404");
				parent.setErrorMessage("User " + parent +" does not exixt");
				return new ResponseEntity<Parent>(parent , HttpStatus.NOT_FOUND);
			}
			// check whether user applied for any job or not LATER
			
			else
			{
				boolean value = parentService.deleteParentByID(parentID);
				if(value)
				{
					parent.setErrorCode("200");
					parent.setErrorMessage("Parent deleted successfuly");
					//return new ResponseEntity<Parent>(parent ,HttpStatus.OK);
				}
				else
				{
					parent.setErrorCode("404");
					parent.setErrorMessage("Parent is not deleted");
					//return new ResponseEntity<Parent>(parent ,HttpStatus.INTERNAL_SERVER_ERROR);
				}			
			}
			return new ResponseEntity<Parent>(parent ,HttpStatus.OK);
			
		}
		
		//http://localhost:8085/SocialCollaborationController/parent/login
				@PostMapping("/parent/login")
				public ResponseEntity<Parent> validateCredential(@RequestBody Parent parent)
				{
					if(parentService.parentValidation(parent.getParentID(), parent.getPassword()) == null)
					{
						parent.setErrorCode("100");
						parent.setErrorMessage("Invalide parent");
						return new ResponseEntity<Parent>(parent  , HttpStatus.UNAUTHORIZED);
					}
					else {
						parent.setErrorCode("200");
						parent.setErrorMessage("Valid parent");
						return new ResponseEntity<Parent>(parent , HttpStatus.OK);
					}
				}
}
