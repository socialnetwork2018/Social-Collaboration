package com.social.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.social.collaboration.dao.TeacherDAO;
import com.social.collaboration.model.Teacher;
import com.social.collaboration.service.TeacherService;

@Transactional
@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherDAO teacherDAO;

	public boolean saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDAO.saveTeacher(teacher);
	}

	public boolean updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDAO.updateTeacher(teacher);
	}

	public List<Teacher> getAllTeacher() {
		// TODO Auto-generated method stub
		return teacherDAO.getAllTeacher();
	}

	public Teacher getTeacherByEmailID(String emailID) {
		// TODO Auto-generated method stub
		return teacherDAO.getTeacherByEmailID(emailID);
	}

	public Teacher getTeacherByTeacherName(String teachername) {
		// TODO Auto-generated method stub
		return teacherDAO.getTeacherByTeacherName(teachername);
	}

	public Teacher getTeacherByID(int teacherID) {
		// TODO Auto-generated method stub
		return teacherDAO.getTeacherByID(teacherID);
	}

	public boolean deleteTeacher(String emailID) {
		// TODO Auto-generated method stub
		return teacherDAO.deleteTeacher(emailID);
	}

	public boolean deleteTeacherByID(int teacherID) {
		// TODO Auto-generated method stub
		return teacherDAO.deleteTeacherByID(teacherID);
	}

	public Teacher teacherValidation(int teacherID, String password) {
		// TODO Auto-generated method stub
		return teacherDAO.teacherValidation(teacherID, password);
	}

}
