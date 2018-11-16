
package com.social.collaboration.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.social.collaboration.dao.TeacherDAO;
import com.social.collaboration.model.Teacher;
import com.social.collaboration.model.Teacher;

@SuppressWarnings("deprecation")
@Repository(value = "teacherDAO")
@Transactional
@EnableTransactionManagement
public class TeacherDAOImpl implements TeacherDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Teacher teacher;

	Logger logger = LoggerFactory.getLogger(Teacher.class);
	
	private int getTeacherMaxID()
	{
		int maxvalue = 2000;
		try {
			maxvalue = (Integer) sessionFactory.getCurrentSession().createQuery("SELECT MAX(teacherID) FROM Teacher").uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2000;
		}
		return maxvalue;
		
	}

	// Save teacher implement
	public boolean saveTeacher(Teacher teacher) {
		logger.debug("starting save method in daoimpl");
		try {
			teacher.setRole("Teacher");
			teacher.setStatus('N');
			teacher.setTeacherID(getTeacherMaxID() + 1);
			teacher.setIsOnline('N');
			sessionFactory.getCurrentSession().save(teacher);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update teacher implementation TO BE CHECKED AGIN SHARHUKH CODE
	/*public boolean updateTeacher(Teacher teacher)
	{
		
	}*/
	
	// Update teacher implementation NIIT way
	public boolean updateTeacher(Teacher teacher) {
		logger.debug("Starting of update teacher method");
		try {
			sessionFactory.getCurrentSession().update(teacher);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	public List<Teacher> getAllTeacher() {
		logger.debug("Starting getAlTeacher method");
		String hql = "from Teacher";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		logger.debug("End of getAllTeacher method");
		return query.list();

		// Alternative query
		// return sessionFactory.getCurrentSession().createQuery("from Teacher").list();
	}

	// Implementation of method to get teacher by email id

	@SuppressWarnings("rawtypes")
	public Teacher getTeacherByEmailID(String emailID) {
		logger.debug("Starting getby emailID method");
		String hql = "from Teacher where emailID='" + emailID + "'";
		logger.debug("------getByemailID query : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		return (Teacher) query.uniqueResult();
	}

	// *************** Get teacher by id method implementation *********
	public Teacher getTeacherByID(int teacherID) {
		logger.info("Starting getTeacherByID method");
		try {
			teacher = sessionFactory.getCurrentSession().get(Teacher.class, teacherID);
			teacher.setErrorCode("200");
			teacher.setErrorMessage("Srtudent found");
			return teacher;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			teacher.setErrorCode("100");
			teacher.setErrorMessage("Srtudent not found");
			return null;
		}
	}

	// *************** Get teacher by teacher name method implementation *********
	public Teacher getTeacherByTeacherName(String teachername) {

		logger.debug("Starting of Mehod get teacher " + teachername);
		try {
			Teacher teacher = sessionFactory.getCurrentSession().get(Teacher.class, teachername);
			teacher.setErrorCode("200");
			teacher.setErrorMessage("Teacher found");
			return teacher;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			teacher = new Teacher();
			teacher.setErrorCode("404");
			teacher.setErrorMessage("Teacher not found");
			e.printStackTrace();
			return null;
		}

	}
	
	// Delete Teacher by ID method implementation
	public boolean deleteTeacherByID(int teacherID) {
		logger.debug("Starting of the delete method");
		try {
			teacher = getTeacherByID(teacherID);
			if (teacher == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(teacher);
			logger.debug("Ending of the delete method");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(" error occured in delete method " + e.getMessage());
			return false;
		}
	}

	// Delete method implementation
	public boolean deleteTeacher(String emailID) {
		logger.debug("Starting of the delete method");
		try {
			teacher = getTeacherByEmailID(emailID);
			if (teacher == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(teacher);
			logger.debug("Ending of the delete method");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(" error occured in delete method " + e.getMessage());
			return false;
		}
	}

	// Teacher credential method implement/ Login method
	@SuppressWarnings("unlikely-arg-type")
	public Teacher teacherValidation(int teacherID, String password) {
		logger.info("Validate teacher Method start");
		try {
			Teacher teacher = sessionFactory.getCurrentSession().get(Teacher.class, teacherID);
			//if ((teacher.getTeacherID().equals(teacherID)) && (teacher.getPassword().equals(password))) 
			if((teacher.getTeacherID() == (teacherID)) && (teacher.getPassword().equals(password))) 
			{
				teacher.setErrorCode("200");
				teacher.setErrorMessage("Teacher found");
				logger.info("You are a valide teacher");
				return teacher;
			} else {
				teacher.setErrorCode("100");
				teacher.setErrorMessage("Teacher not found, Incorrect password");
				return teacher;
			}
		} catch (HibernateException e) {
			Teacher teacher = new Teacher();
			teacher.setErrorCode("100");
			teacher.setErrorMessage("Teacher not found");
			logger.error("Teacher not found in Database");
			return teacher;
			// e.printStackTrace();
		}

		/*
		 * return (Teacher) sessionFactory.getCurrentSession()
		 * .createCriteria(Teacher.class) .add(Restrictions.eq("teachername",
		 * teachername)) .add(Restrictions.eq("password", password)) .uniqueResult();
		 */
		// Another alternative of teacher login
	}

	
}
