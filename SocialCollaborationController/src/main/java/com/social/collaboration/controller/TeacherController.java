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
import com.social.collaboration.dao.TeacherDAO;
import com.social.collaboration.model.Teacher;
import com.social.collaboration.model.Teacher;
import com.social.collaboration.service.TeacherService;

@RestController
public class TeacherController {

	Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	TeacherService teacherService;

	@Autowired
	TeacherDAO teacherDAO;

	@Autowired
	Teacher teacher;

	@Autowired
	HttpSession httpSession;

	// Add new teacher
	// http://localhost:8580/SocialCollaborationController/add/teacher
	@PostMapping("/teacher/add")
	public ResponseEntity<Teacher> addNewTeacher(@RequestBody Teacher teacher) {
		// logger.info("saveTeachery method starts");

		// Add more conditions ABBAS NIIT
		boolean value = teacherService.saveTeacher(teacher);
		if (value == true) {
			teacher.setErrorCode("200");
			teacher.setErrorMessage("Teacher added successfuly");
		} else {
			teacher.setErrorCode("100");
			teacher.setErrorMessage("Fail to add Teacher");
			return new ResponseEntity<Teacher>(teacher, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}

	// Edit teacher
	// http://localhost:8580/SocialCollaborationController/update/teacher
	@PutMapping("teacher/update")
	public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
		// Add more conditions ABBAS NIIT

		// if teacher does not exist
		/*
		 * if(teacherService.getTeacherByTeacherName(teacher.getTeachername())==null)
		 * //NOT WORKING { teacher.setErrorCode("404");
		 * teacher.setErrorMessage("Teacher " +teacher.getTeachername() + " not found");
		 * return new ResponseEntity<Teacher>(teacher, HttpStatus.NOT_FOUND); }
		 */

		if (teacher != null) {
			boolean value = teacherService.updateTeacher(teacher);
			if (value == true) {
				teacher.setErrorCode("200");
				teacher.setErrorMessage("Teacher updated Successfully");
			} else {
				teacher.setErrorCode("100");
				teacher.setErrorMessage("Add Teacher Failed");
				return null;
			}
		}

		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}

	// Delete teacher by teacher name
	// http://localhost:8085/SocialCollaborationController/teacher/delete/teachername
	@DeleteMapping("delete_teacher-{teachername}")
	public ResponseEntity<Teacher> deleteTeacherName(@PathVariable("teachername") String teachername) {
		// Check whether teacher exit or not
		// teacher = teacherService.getTeacherByTeacherName(teachername);
		/*
		 * if(teacher == null) { //teacher = new Teacher(); teacher.setErrorCode("404");
		 * teacher.setErrorMessage("Teacher " + teachername +" does not exixt"); return
		 * new ResponseEntity<Teacher>(teacher , HttpStatus.NOT_FOUND); }
		 */
		// check whether teacher applied for any job or not LATER

		if (teacher != null) {
			boolean value = teacherService.deleteTeacher(teachername);
			if (value) {
				teacher.setErrorCode("200");
				teacher.setErrorMessage("Teacher deleted successfuly");
				// return new ResponseEntity<Teacher>(teacher ,HttpStatus.OK);
			} else {
				teacher.setErrorCode("404");
				teacher.setErrorMessage("Teacher is not deleted");
				return new ResponseEntity<Teacher>(teacher, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);

	}

	// Get teacher by emailID NOT WORKING TO BE CHECKED
	// http://localhost:8085/SocialCollaborationController/getTeacher-{emailID}
	@GetMapping("/getTeacher-{emailID}")
	public ResponseEntity<Teacher> getTeacherEmailID(@PathVariable("emailID") String emailID) {
		teacher = teacherService.getTeacherByEmailID(emailID);
		if (teacher == null) {
			teacher = new Teacher();
			teacher.setErrorCode("100");
			teacher.setErrorMessage("Teacher " + emailID + "is not found");
			return new ResponseEntity<Teacher>(teacher, HttpStatus.NOT_FOUND);
		} else {

			teacher.setErrorCode("200");
			teacher.setErrorMessage("Teacher " + emailID + " is found");
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
	}


	// http://localhost:8085/SocialCollaborationController/getteacherid-{teacherID}
	@GetMapping("/getteacherid/{teacherID}")
	public ResponseEntity<Teacher> getTeacherbyid(@PathVariable("teacherID") int teacherID)
	{
		teacher = teacherService.getTeacherByID(teacherID);
		if(teacher  == null)
		{
			teacher = new Teacher();
			teacher.setErrorCode("100");
			teacher.setErrorMessage("Teacher " + teacherID + " is not found");
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		 else {

				teacher.setErrorCode("200");
				teacher.setErrorMessage("Teacher " + teacherID + " is found");
				return new ResponseEntity<Teacher>(teacher, HttpStatus.NOT_FOUND);
			}
	}
	
	// http://localhost:8085/SocialCollaborationController/getallteacherlist
	@GetMapping("/getallteacherlist")
	public ResponseEntity<List<Teacher>>getAllTeachertList()
	{
		List<Teacher> teacherList = teacherService.getAllTeacher();
		if(teacherList.isEmpty())
		{
			teacher.setErrorCode("100");
			teacher.setErrorMessage("Teacher are not available");
		}
		else
		{
			//DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); LATER
			for(Teacher teacher : teacherList)
			{
				teacher.setErrorCode("200");
				teacher.setErrorMessage("Teacher found");
				/*if(teacher.getDob() != null)		TO BE CHECKED LATER
					teacher.setBirthdate(df.format(teacher.getDob()));*/
			}
		}
		return new ResponseEntity<List<Teacher>>(teacherList , HttpStatus.OK);
	}
	
	
		//http://localhost:8085/SocialCollaborationController/teacher/delete/{teachername}
		@DeleteMapping("/teacher/delete/{teacherID}")
		public ResponseEntity<Teacher> deleteteacherid(@PathVariable("teacherID") int teacherID)
		{
			//Check whether teacher exit or not
			teacher = teacherService.getTeacherByID(teacherID);
			if(teacher == null)
			{
				//teacher = new Teacher();
				teacher.setErrorCode("404");
				teacher.setErrorMessage("Teacher " + teacher +" does not exixt");
				return new ResponseEntity<Teacher>(teacher , HttpStatus.NOT_FOUND);
			}
			// check whether teacher applied for any job or not LATER
			
			else
			{
				boolean value = teacherService.deleteTeacherByID(teacherID);
				if(value)
				{
					teacher.setErrorCode("200");
					teacher.setErrorMessage("Teacher deleted successfuly");
					//return new ResponseEntity<Teacher>(teacher ,HttpStatus.OK);
				}
				else
				{
					teacher.setErrorCode("404");
					teacher.setErrorMessage("Teacher is not deleted");
					//return new ResponseEntity<Teacher>(teacher ,HttpStatus.INTERNAL_SERVER_ERROR);
				}			
			}
			return new ResponseEntity<Teacher>(teacher ,HttpStatus.OK);		
		}
		
		//http://localhost:8085/SocialCollaborationController/teacher/login
		@PostMapping("/teacher/login")
		public ResponseEntity<Teacher> validateCredential(@RequestBody Teacher teacher)
		{
			if(teacherService.teacherValidation(teacher.getTeacherID(), teacher.getPassword()) == null)
			{
				teacher.setErrorCode("100");
				teacher.setErrorMessage("Invalide teacher");
				return new ResponseEntity<Teacher>(teacher  , HttpStatus.UNAUTHORIZED);
			}
			else {
				teacher.setErrorCode("200");
				teacher.setErrorMessage("Valid teacher");
				return new ResponseEntity<Teacher>(teacher , HttpStatus.OK);
			}
		}

}
