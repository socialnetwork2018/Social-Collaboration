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
import com.social.collaboration.dao.StudentDAO;
import com.social.collaboration.model.Student;
import com.social.collaboration.model.Student;
import com.social.collaboration.model.User;
import com.social.collaboration.service.StudentService;

@RestController
public class StudentController {

	Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService studentService;

	@Autowired
	StudentDAO studentDAO;

	@Autowired
	Student student;

	@Autowired
	HttpSession httpSession;

	// Add new student
	// http://localhost:8580/SocialCollaborationController/add/student
	@PostMapping("/student/add")
	public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
		// logger.info("saveStudenty method starts");

		// Add more conditions ABBAS NIIT
		boolean value = studentService.saveStudent(student);
		if (value == true) {
			student.setErrorCode("200");
			student.setErrorMessage("Student added successfuly");
		} else {
			student.setErrorCode("100");
			student.setErrorMessage("Fail to add Student");
			return new ResponseEntity<Student>(student, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	// Edit student
	// http://localhost:8580/SocialCollaborationController/update/student
	@PutMapping("student/update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		// Add more conditions ABBAS NIIT

		// if student does not exist
		/*
		 * if(studentService.getStudentByStudentName(student.getStudentname())==null)
		 * //NOT WORKING { student.setErrorCode("404");
		 * student.setErrorMessage("Student " +student.getStudentname() + " not found");
		 * return new ResponseEntity<Student>(student, HttpStatus.NOT_FOUND); }
		 */

		if (student != null) {
			boolean value = studentService.updateStudent(student);
			if (value == true) {
				student.setErrorCode("200");
				student.setErrorMessage("Student updated Successfully");
			} else {
				student.setErrorCode("100");
				student.setErrorMessage("Add Student Failed");
				return null;
			}
		}

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	// Delete student by student name
	// http://localhost:8085/SocialCollaborationController/student/delete/studentname
	@DeleteMapping("delete_student-{studentname}")
	public ResponseEntity<Student> deleteStudentName(@PathVariable("studentname") String studentname) {
		// Check whether student exit or not
		// student = studentService.getStudentByStudentName(studentname);
		/*
		 * if(student == null) { //student = new Student(); student.setErrorCode("404");
		 * student.setErrorMessage("Student " + studentname +" does not exixt"); return
		 * new ResponseEntity<Student>(student , HttpStatus.NOT_FOUND); }
		 */
		// check whether student applied for any job or not LATER

		if (student != null) {
			boolean value = studentService.deleteStudent(studentname);
			if (value) {
				student.setErrorCode("200");
				student.setErrorMessage("Student deleted successfuly");
				// return new ResponseEntity<Student>(student ,HttpStatus.OK);
			} else {
				student.setErrorCode("404");
				student.setErrorMessage("Student is not deleted");
				return new ResponseEntity<Student>(student, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);

	}

	// Get user by emailID NOT WORKING TO BE CHECKED
	// http://localhost:8085/SocialCollaborationController/getStudent-{emailID}
	@GetMapping("/getStudent-{emailID}")
	public ResponseEntity<Student> getStudentEmailID(@PathVariable("emailID") String emailID) {
		student = studentService.getStudentByEmailID(emailID);
		if (student == null) {
			student = new Student();
			student.setErrorCode("100");
			student.setErrorMessage("Student " + emailID + "is not found");
			return new ResponseEntity<Student>(student, HttpStatus.NOT_FOUND);
		} else {

			student.setErrorCode("200");
			student.setErrorMessage("User " + emailID + " is found");
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
	}


	// http://localhost:8085/SocialCollaborationController/getstudentid-{studentID}
	@GetMapping("/getstudentid/{studentID}")
	public ResponseEntity<Student> getStudentbyid(@PathVariable("studentID") int studentID)
	{
		student = studentService.getStudentByID(studentID);
		if(student  == null)
		{
			student = new Student();
			student.setErrorCode("100");
			student.setErrorMessage("Student " + studentID + " is not found");
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		 else {

				student.setErrorCode("200");
				student.setErrorMessage("User " + studentID + " is found");
				return new ResponseEntity<Student>(student, HttpStatus.NOT_FOUND);
			}
	}
	
	// http://localhost:8085/SocialCollaborationController/getallstudentlist
	@GetMapping("/getallstudentlist")
	public ResponseEntity<List<Student>>getAllStudenttList()
	{
		List<Student> studentList = studentService.getAllStudent();
		if(studentList.isEmpty())
		{
			student.setErrorCode("100");
			student.setErrorMessage("Student are not available");
		}
		else
		{
			//DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); LATER
			for(Student student : studentList)
			{
				student.setErrorCode("200");
				student.setErrorMessage("Student found");
				/*if(user.getDob() != null)		TO BE CHECKED LATER
					user.setBirthdate(df.format(user.getDob()));*/
			}
		}
		return new ResponseEntity<List<Student>>(studentList , HttpStatus.OK);
	}
	
	
		//http://localhost:8085/SocialCollaborationController/student/delete/{username}
		@DeleteMapping("/student/delete/{studentID}")
		public ResponseEntity<Student> deletestudentid(@PathVariable("studentID") int studentID)
		{
			//Check whether user exit or not
			student = studentService.getStudentByID(studentID);
			if(student == null)
			{
				//user = new User();
				student.setErrorCode("404");
				student.setErrorMessage("User " + student +" does not exixt");
				return new ResponseEntity<Student>(student , HttpStatus.NOT_FOUND);
			}
			// check whether user applied for any job or not LATER
			
			else
			{
				boolean value = studentService.deleteStudentByID(studentID);
				if(value)
				{
					student.setErrorCode("200");
					student.setErrorMessage("Student deleted successfuly");
					//return new ResponseEntity<Student>(student ,HttpStatus.OK);
				}
				else
				{
					student.setErrorCode("404");
					student.setErrorMessage("Student is not deleted");
					//return new ResponseEntity<Student>(student ,HttpStatus.INTERNAL_SERVER_ERROR);
				}			
			}
			return new ResponseEntity<Student>(student ,HttpStatus.OK);
			
		}
		
		//http://localhost:8085/SocialCollaborationController/student/login
				@PostMapping("/student/login")
				public ResponseEntity<Student> validateCredential(@RequestBody Student student)
				{
					if(studentService.studentValidation(student.getStudentID(), student.getPassword()) == null)
					{
						student.setErrorCode("100");
						student.setErrorMessage("Invalide student");
						return new ResponseEntity<Student>(student  , HttpStatus.UNAUTHORIZED);
					}
					else {
						student.setErrorCode("200");
						student.setErrorMessage("Valid student");
						return new ResponseEntity<Student>(student , HttpStatus.OK);
					}
				}
}
