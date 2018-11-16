package com.social.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.social.collaboration.dao.StudentDAO;
import com.social.collaboration.model.Student;
import com.social.collaboration.service.StudentService;

@Transactional
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentDAO studentDAO;

	public boolean saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDAO.saveStudent(student);
	}

	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDAO.updateStudent(student);
	}

	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentDAO.getAllStudent();
	}

	public Student getStudentByEmailID(String emailID) {
		// TODO Auto-generated method stub
		return studentDAO.getStudentByEmailID(emailID);
	}

	public Student getStudentByStudentName(String studentname) {
		// TODO Auto-generated method stub
		return studentDAO.getStudentByStudentName(studentname);
	}

	public Student getStudentByID(int studentID) {
		// TODO Auto-generated method stub
		return studentDAO.getStudentByID(studentID);
	}

	public boolean deleteStudent(String emailID) {
		// TODO Auto-generated method stub
		return studentDAO.deleteStudent(emailID);
	}

	public Student studentValidation(int studentID, String password) {
		// TODO Auto-generated method stub
		return studentDAO.studentValidation(studentID, password);
	}

	public boolean deleteStudentByID(int studentID) {
		// TODO Auto-generated method stub
		return false;
	}

}
