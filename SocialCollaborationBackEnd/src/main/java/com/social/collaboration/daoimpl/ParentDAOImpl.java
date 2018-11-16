
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

import com.social.collaboration.dao.ParentDAO;
import com.social.collaboration.model.Parent;

@SuppressWarnings("deprecation")
@Repository(value = "parentDAO")
@Transactional
@EnableTransactionManagement
public class ParentDAOImpl implements ParentDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Parent parent;

	Logger logger = LoggerFactory.getLogger(Parent.class);
	
	private int getParentMaxID()
	{
		int maxvalue = 3000;
		try {
			maxvalue = (Integer) sessionFactory.getCurrentSession().createQuery("SELECT MAX(parentID) FROM Parent")
					.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2000;
		}
		return maxvalue;
		
	}

	// Save parent implement
	public boolean saveParent(Parent parent) {
		logger.debug("starting save method in daoimpl");
		try {
			parent.setRole("Parent");
			parent.setStatus('N');
			parent.setParentID(getParentMaxID() + 1);
			parent.setIsOnline('N');
			//parent.setDateReg(new Date());
			sessionFactory.getCurrentSession().save(parent);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update parent implementation TO BE CHECKED AGIN SHARHUKH CODE
	/*public boolean updateParent(Parent parent)
	{
		
	}*/
	
	// Update parent implementation NIIT way
	public boolean updateParent(Parent parent) {
		logger.debug("Starting of update parent method");
		try {
			sessionFactory.getCurrentSession().update(parent);
			parent.setRole("Parent");
			parent.setStatus('N');
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	public List<Parent> getAllParent() {
		logger.debug("Starting getAlParent method");
		String hql = "from Parent";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		logger.debug("End of getAllParent method");
		return query.list();

		// Alternative query
		// return sessionFactory.getCurrentSession().createQuery("from Parent").list();
	}

	// Implementation of method to get parent by email id

	@SuppressWarnings("rawtypes")
	public Parent getParentByEmailID(String emailID) {
		logger.debug("Starting getby emailID method");
		String hql = "from Parent where emailID='" + emailID + "'";
		logger.debug("------getByemailID query : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		return (Parent) query.uniqueResult();
	}

	// *************** Get parent by id method implementation *********
	public Parent getParentByID(int parentID) {
		logger.info("Starting of Meyhod get parent by ID" + parentID);
		try {
			Parent parent = sessionFactory.getCurrentSession().get(Parent.class, parentID);
			parent.setErrorCode("200");
			parent.setErrorMessage("Parent " + parentID+" found");
			return parent;
		} catch (HibernateException e) {
			parent = new Parent();
			parent.setErrorCode("404");
			parent.setErrorMessage("Parent " + parentID + " not found");
			e.printStackTrace();
			return null;
		}
	}

	// *************** Get parent by parent name method implementation *********
	public Parent getParentByParentName(String parentname) {

		logger.debug("Starting of Mehod get parent " + parentname);
		try {
			Parent parent = sessionFactory.getCurrentSession().get(Parent.class, parentname);
			parent.setErrorCode("200");
			parent.setErrorMessage("Parent found");
			return parent;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			parent = new Parent();
			parent.setErrorCode("404");
			parent.setErrorMessage("Parent not found");
			e.printStackTrace();
			return null;
		}

	}
	
	
	// Delete method implementation
		public boolean deleteParentByID(int parentID) {
			logger.debug("Starting of the delete method");
			try {
				parent = getParentByID(parentID);
				if (parent == null) {
					return false;
				}

				sessionFactory.getCurrentSession().delete(parent);
				logger.debug("Ending of the delete method");
				return true;
			} catch (HibernateException e) {
				e.printStackTrace();
				logger.error(" error occured in delete method " + e.getMessage());
				return false;
			}
		}

	// Delete method implementation
	public boolean deleteParent(String emailID) {
		logger.debug("Starting of the delete method");
		try {
			parent = getParentByEmailID(emailID);
			if (parent == null) {
				return false;
			}

			sessionFactory.getCurrentSession().delete(parent);
			logger.debug("Ending of the delete method");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(" error occured in delete method " + e.getMessage());
			return false;
		}
	}

	// Parent credential method implement/ Login method
	public Parent parentValidation(int parentID, String password) {
		logger.info("Validate parent Method start");
		try {
			Parent parent = sessionFactory.getCurrentSession().get(Parent.class, parentID);
			if ((parent.getParentID() == parentID) && (parent.getPassword().equals(password))) {
				parent.setErrorCode("200");
				parent.setErrorMessage("Parent found");
				logger.info("You are a valide parent");
				return parent;
			} else {
				parent.setErrorCode("100");
				parent.setErrorMessage("Parent not found, Incorrect password");
				return parent;
			}
		} catch (HibernateException e) {
			Parent parent = new Parent();
			parent.setErrorCode("100");
			parent.setErrorMessage("Parentname not found");
			logger.error("Parent not found in Database");
			return parent;
			// e.printStackTrace();
		}

		/*
		 * return (Parent) sessionFactory.getCurrentSession()
		 * .createCriteria(Parent.class) .add(Restrictions.eq("parentname",
		 * parentname)) .add(Restrictions.eq("password", password)) .uniqueResult();
		 */
		// Another alternative of parent login
	}
}
