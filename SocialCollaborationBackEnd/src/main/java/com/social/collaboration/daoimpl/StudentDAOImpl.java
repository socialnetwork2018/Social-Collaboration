
package com.social.collaboration.daoimpl;

import java.util.Date;
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

import com.social.collaboration.dao.StudentDAO;
import com.social.collaboration.model.Student;

@SuppressWarnings("deprecation")
@Repository(value = "studentDAO")
@Transactional
@EnableTransactionManagement
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Student student;

	Logger logger = LoggerFactory.getLogger(Student.class);

	private int getStudentMaxID() {
		int maxvalue = 3000;
		try {
			maxvalue = (Integer) sessionFactory.getCurrentSession().createQuery("SELECT MAX(studentID) FROM Student")
					.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2000;
		}
		return maxvalue;

	}

	// Save student implement
	public boolean saveStudent(Student student) {
		logger.debug("starting save method in daoimpl");
		try {
			student.setRole("Student");
			student.setStatus('N');
			student.setStudentID(getStudentMaxID() + 1);
			student.setIsOnline('N');
			// student.setDateReg(new Date());
			student.setBatch(new Date());
			sessionFactory.getCurrentSession().save(student);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update student implementation TO BE CHECKED AGIN SHARHUKH CODE
	/*
	 * public boolean updateStudent(Student student) {
	 * 
	 * }
	 */

	// CHECK UPDATE METHOD IMPLEMENTATION OF SHARHUKH CODE
	// Update student implementation NIIT way
	public boolean updateStudent(Student student) {
		logger.debug("Starting of update student method");
		try {
			sessionFactory.getCurrentSession().update(student);
			student.setRole("Student");
			student.setStatus('N');
			student.setBatch(new Date());
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudent() {
		logger.debug("Starting getAlStudent method");
		String hql = "from Student";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		logger.debug("End of getAllStudent method");
		return query.list();

		// Alternative query
		// return sessionFactory.getCurrentSession().createQuery("from Student").list();

		// Alternative query
		// return (List<Student>)
		// sessionFactory.getCurrentSession().createCriteria(Student.class)
		// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	// Implementation of method to get student by email id NOT WORKING YET. CHECK
	// WITH SHARUK AND ABBAS
	/*
	 * @SuppressWarnings("rawtypes") public Student getStudentByEmailID(String
	 * emailID) { logger.debug("Starting getby emailID method"); String hql =
	 * "from Student where emailID='" + emailID + "'";
	 * logger.debug("------getByemailID query : " + hql); //Query query =
	 * sessionFactory.openSession().createQuery(hql); Query query =
	 * sessionFactory.getCurrentSession().createQuery(hql); return (Student)
	 * query.uniqueResult(); }
	 */

	// Implementation of method to get student by email id NOT WORKING YET. CHECK
	// WITH SHARUK AND ABBAS
	public Student getStudentByEmailID(String emailID) // NOT WORKING
	{
		logger.debug("Starting of Method Get User " + emailID);
		try {
			student = sessionFactory.getCurrentSession().get(Student.class, emailID);
			student.setErrorCode("200");
			student.setErrorMessage("Student Found");
			return student;
		} catch (Exception ex) {
			Student student = new Student();
			ex.printStackTrace();
			student.setErrorCode("404");
			student.setErrorMessage("Student Not Found");
			return null;
		}
	}

// ++++++++++++ TO BE IMPLEMENTED +++++++++++++++++++++ DONE
	// *************** Get student by id method implementation *********
	public Student getStudentByID(int studentID) {
		logger.info("Starting getStudentByID method");
		try {
			student = sessionFactory.getCurrentSession().get(Student.class, studentID);
			student.setErrorCode("200");
			student.setErrorMessage("Srtudent found");
			return student;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			student.setErrorCode("100");
			student.setErrorMessage("Srtudent not found");
			return null;
		}
	}

	// *************** Get student by student name method implementation *********
	// TO BE REMOVED
	public Student getStudentByStudentName(String studentname) {

		logger.debug("Starting of Mehod get student " + studentname);
		try {
			Student student = sessionFactory.getCurrentSession().get(Student.class, studentname);
			student.setErrorCode("200");
			student.setErrorMessage("Student found");
			return student;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			student = new Student();
			student.setErrorCode("404");
			student.setErrorMessage("Student not found");
			e.printStackTrace();
			return null;
		}

	}

	// NOT WORKING
	public boolean deleteStudentByID(int studentID) {
		logger.info("Starting of the delete method by student id");
		try {
			student = getStudentByID(studentID);
			if (student == null) {
				return false;
			}
			sessionFactory.getCurrentSession().delete(student);
			logger.debug("Ending of the delete method");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(" error occured in delete method " + e.getMessage());
			return false;
		}
	}

	// Delete student by emaiID method implementation TO BE CHECKED
	public boolean deleteStudent(String emailID) {
		logger.debug("Starting of the delete method");
		try {
			student = getStudentByEmailID(emailID);
			if (student == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(student);
			logger.debug("Ending of the delete method");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(" error occured in delete method " + e.getMessage());
			return false;
		}
	}

	// Delete student by student name method implementation TO BE REMOVED
	public boolean deleteStudentByName(String studentname) {
		logger.info("Delete student  by name starts");
		try {
			student = getStudentByStudentName(studentname);
			if (student == null) {
				return false;
			}
			sessionFactory.getCurrentSession().delete(studentname);
			logger.debug("Ending of delete method");
			return true;
		} catch (HibernateException e) {
			logger.error(" error occured in delete method " + e.getMessage());
			return false;
		}

	}

	// Student credential method implement/ Login method
	@SuppressWarnings("unlikely-arg-type")
	public Student studentValidation(int studentID, String password) {
		logger.info("Validate student Method start");
		try {
			Student student = sessionFactory.getCurrentSession().get(Student.class, studentID);
			// if ((student.getEmailID().equals(studentID)) &&
			// (student.getPassword().equals(password)))
			if ((student.getStudentID() == studentID) && (student.getPassword().equals(password))) {
				student.setErrorCode("200");
				student.setErrorMessage("Student found");
				logger.info("You are a valide student");
				return student;
			} else {
				student.setErrorCode("100");
				student.setErrorMessage("Student not found, Incorrect password");
				return student;
			}
		} catch (HibernateException e) {
			Student student = new Student();
			student.setErrorCode("100");
			student.setErrorMessage("Studentname not found");
			logger.error("Student not found in Database");
			return student;
			// e.printStackTrace();
		}

		/*
		 * return (Student) sessionFactory.getCurrentSession()
		 * .createCriteria(Student.class) .add(Restrictions.eq("studentname",
		 * studentname)) .add(Restrictions.eq("password", password)) .uniqueResult();
		 */
		// Another alternative of student login
	}
}
