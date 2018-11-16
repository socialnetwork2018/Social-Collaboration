
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

import com.social.collaboration.dao.UserDAO;
import com.social.collaboration.model.User;

@SuppressWarnings("deprecation")
@Repository(value = "userDAO")
@Transactional
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	User user;

	Logger logger = LoggerFactory.getLogger(User.class);

	// Save user implement
	public boolean saveUser(User user) {
		logger.debug("starting save method in daoimpl");
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update user implementation TO BE CHECKED AGIN SHARHUKH CODE OR MINE
	public boolean updateUser(User user) {
		logger.debug("Starting of update user method");
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<User> getAllUser() {
		logger.debug("Starting getAlUser method");
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		logger.debug("End of getAllUser method");
		return query.list();

		// Alternative query
		// return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	// Implementation of method to get user by email id
	@SuppressWarnings("rawtypes")
	public User getUserByEmailID(String emailID) {
		logger.debug("Starting getby emailID method");
		String hql = "from User where emailID='" + emailID + "'";
		logger.debug("------getByemailID query : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (User) query.uniqueResult();
	}
	
	//Ftech Users by Username
	public User getUserByUserName(String username) {
		logger.debug("Starting of Mehod get user " + username);
		try {
			User user = sessionFactory.getCurrentSession().get(User.class, username);
			user.setErrorCode("200");
			user.setErrorMessage("User found");
			return user;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User not found");
			e.printStackTrace();
			return null;
		}

	}

	// Delete method implementation
	public boolean deleteUserByEmailID(String emailID) {
		logger.debug("Starting of the delete method");
		try {
			user = getUserByEmailID(emailID);
			if (user == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(user);
			logger.debug("Ending of the delete method");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(" error occured in delete method " + e.getMessage());
			return false;
		}
	}
	
	// Delete method implementation
		public boolean deleteUserByName(String username) {
			logger.debug("Starting of the delete method");
			try {
				user = getUserByUserName(username);
				if (user == null) {
					return false;
				}

				sessionFactory.getCurrentSession().delete(user);
				logger.debug("Ending of the delete method");
				return true;
			} catch (HibernateException e) {
				e.printStackTrace();
				logger.error(" error occured in delete method " + e.getMessage());
				return false;
			}
		}


	// User credential method implement/ Login method
	public User userValidation(String username, String password) {
		logger.info("Validate user Method start");
		try {
			User user = sessionFactory.getCurrentSession().get(User.class, username);
			if ((user.getUsername().equals(username)) && (user.getPassword().equals(password))) {
				user.setErrorCode("200");
				user.setErrorMessage("User found");
				logger.info("You are a valide user");
				return user;
			} else {
				user.setErrorCode("100");
				user.setErrorMessage("User not found, Incorrect password");
				return user;
			}
		} catch (HibernateException e) {
			User user = new User();
			user.setErrorCode("100");
			user.setErrorMessage("Username not found");
			logger.error("User not found in Database");
			return user;
			// e.printStackTrace();
		}

		/*
		 * return (User) sessionFactory.getCurrentSession() .createCriteria(User.class)
		 * .add(Restrictions.eq("username", username)) .add(Restrictions.eq("password",
		 * password)) .uniqueResult();
		 */
		// Another alternative of user login
	}
}
