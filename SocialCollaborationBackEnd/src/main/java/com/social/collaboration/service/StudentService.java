package com.social.collaboration.service;

import java.util.List;

import com.social.collaboration.model.Student;

public interface StudentService {

	// Method to save student
	public boolean saveStudent(Student student);

	// Method to update student
	public boolean updateStudent(Student student);

	// Fetch all the students
	public List<Student> getAllStudent();

	// Fetch student based on email id
	public Student getStudentByEmailID(String emailID);

	// Fetch student based on email studentname
	public Student getStudentByStudentName(String studentname);

	// Fetch student based on email studentname
	public Student getStudentByID(int studentID);

	// Method to delete student
	public boolean deleteStudent(String emailID);
	
	// Method to delete student by ID
		public boolean deleteStudentByID(int studentID);

	// Login method
	public Student studentValidation(int studentID, String password);

	// Add more methods

}
