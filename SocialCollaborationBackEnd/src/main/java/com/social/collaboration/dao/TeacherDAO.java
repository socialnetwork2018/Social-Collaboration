package com.social.collaboration.dao;

import java.util.List;

import com.social.collaboration.model.Teacher;

public interface TeacherDAO {

	// Method to save teacher
	public boolean saveTeacher(Teacher teacher);

	// Method to update teacher
	public boolean updateTeacher(Teacher teacher);

	// Fetch all the teachers
	public List<Teacher> getAllTeacher();

	// Fetch teacher based on email id
	public Teacher getTeacherByEmailID(String emailID);

	// Fetch teacher based on email teachername
	public Teacher getTeacherByTeacherName(String teachername);

	// Fetch teacher based on email teachername
	public Teacher getTeacherByID(int teacherID);

	// Method to delete teacher
	public boolean deleteTeacher(String emailID);
	
	// Method to delete teacher by id
		public boolean deleteTeacherByID(int teacherID);

	// Login method
	public Teacher teacherValidation(int teacherID, String password);

	// Add more methods

}
