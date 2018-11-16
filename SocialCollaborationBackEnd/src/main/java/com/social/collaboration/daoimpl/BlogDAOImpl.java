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
import org.springframework.transaction.annotation.Transactional;

import com.social.collaboration.dao.BlogDAO;
import com.social.collaboration.model.Blog;

@SuppressWarnings("deprecation")
@Transactional
@Repository(value = "blogDAO")
public class BlogDAOImpl implements BlogDAO {

	private static final Logger logger = LoggerFactory.getLogger(BlogDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	// **************** Incrementation of blog ID *****************
	private int getMaxBlogID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) sessionFactory.getCurrentSession().createQuery("select max(blog_id) from Blog ")
					.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return 100;
		}
		return maxValue;

	}

	// ***************** Create new blog implementation method *******************
	public boolean addBlog(Blog blog) {
		logger.info("Add blog method starting");
		try {
			blog.setBlog_status("Submitted");
			blog.setCreatedDate(new Date());
			blog.setBlog_id(getMaxBlogID() + 1);
			sessionFactory.getCurrentSession().save(blog);
			// sessionFactory.getCurrentSession().saveOrUpdate(blog); TO BE CHECKED LATER
			logger.info("Add blog success");
			return true;
		} catch (HibernateException e) {
			logger.error("Error adding Blog");
			e.printStackTrace();
			return false;
		}
	}

	// ************** udate blog blog method implementation ******************

	public boolean updateBlog(Blog blog) {
		logger.info("Update blog method starts");
		Blog saveBlog = blog;
		{
			String title = saveBlog.getBlog_title();
			String hlq = "FROM Blog WHERE blog_title = '" + title + " '";

			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hlq);
			blog = (Blog) query.uniqueResult(); // sessionFactory.getCurrentSession().delete(blog);
			logger.info("processing request");
		}

		try {
			saveBlog.setBlog_status("Blog updated");
			sessionFactory.getCurrentSession().save(saveBlog);
			logger.info("Blog updated successfuly");
			return true;
		} catch (HibernateException e) {
			logger.info("Error while updating blog");
			e.printStackTrace();
			return false;
		}
	}

	// UDPDATE METHOD IS WORKING HERE. ABOVE CODE IS CORRECT AN ALTERNATIVE
	// IMPLEMENTATION

	/*
	 * public boolean updateBlog(Blog blog) { try {
	 * blog.setBlog_status("Submitted");
	 * sessionFactory.getCurrentSession().update(blog); return true; } catch
	 * (HibernateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); return false; } }
	 */

	// **************** Approved blog by Admin only method *****************

	@SuppressWarnings("rawtypes")
	public boolean approveBlog(Blog blog) {
		logger.info("Approve Blog by Admin only");
		Blog saveBlog = blog;
		{
			String title = saveBlog.getBlog_title();
			String hql = "FROM Blog WHERE blog_title = '" + title + "'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			blog = (Blog) query.uniqueResult();
			logger.info("Proccesing Request of approved blog");
		}
		try {
			sessionFactory.getCurrentSession().save(saveBlog);
			logger.info("Blog Udated successfuly");
			return true;
		} catch (HibernateException e) {
			logger.error("Error while adding Blog");
			e.printStackTrace();
			return false;
		}
	}

	// Approved blog by Admin only method ALTERNATIVE MTHOD IMPLEMENTATION
	/*public boolean approveBlog(Blog blog)
	{
		try {
			blog.setBlog_status("Approved");
			sessionFactory.getCurrentSession().update(blog);;
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}*/
	 

	// **************** DELETE BLOG .... int blog was String blog_title TO BE
	// CHECKED **************
	public boolean deleteBlog(int blog_id) {
		logger.info("Delete blog method starting");
		try {
			Blog blog = (Blog) sessionFactory.getCurrentSession().get(Blog.class, blog_id);
			sessionFactory.getCurrentSession().delete(blog);
			logger.info("Blog deleted successfuly");
			return true;
		} catch (HibernateException e) {
			logger.info("Error occured while deleting blog");
			e.printStackTrace();
			return false;
		}
	}

	// ************** get blog by title by title ****************
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public Blog getBlogByTitle(String blog_title) {
		logger.info("Get Blog method started");
		try {
			String hql_string = "FROM Blog WHERE blog_title = '" + blog_title + "'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			logger.info("Blog Retrieved");
			return (Blog) query.uniqueResult();
		} catch (Exception ex) {
			logger.error("Error Getting Blog");
			ex.printStackTrace();
			return null;
		}
	}

	// *********** get blog by user name meethod implementation ***************
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public List<Blog> getBlogByUser(String username) {
		logger.info("Blog List by User started");
		try {
			String hql_string = "FROM Blog WHERE username = '" + username + "'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			@SuppressWarnings("unchecked")
			List<Blog> list = query.list();
			if (list != null && !list.isEmpty()) {
				logger.info("Blog List Retrieved");
				return list;
			}
			logger.warn("Blog List Mostly Empty");
			return null;
		} catch (Exception ex) {
			logger.error("Error Getting Blog List");
			ex.printStackTrace();
			return null;
		}
	}

	// **************** List of blog by blog ID method implementation
	// ********************
	public List<Blog> getBlogByID(String blog_id) {
		// *********************** TO BE IMPLEMENTED LATER
		// **********************************
		return null;
	}

	// *********** get all user method implementation
	// ************************************
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public List<Blog> getAllBlogs() {
		// logger.info("getAllBlogs method starting");
		try {
			String hlq = "FROM Blog";
			Query query = sessionFactory.getCurrentSession().createQuery(hlq);
			@SuppressWarnings("unchecked")
			List<Blog> listBlogs = query.list();
			if (listBlogs != null && !listBlogs.isEmpty()) {
				logger.info("List of blog retrived");
				return listBlogs;
			}

			logger.warn("BLog List must be empty");
			return null;
		} catch (HibernateException e) {
			logger.info("Error while retrieving blog list");
			e.printStackTrace();
			return null;
		}
	}

	// ********* get approved blog list *********************
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public List<Blog> getApprovedBlogs() {
		logger.info("Approved Blog list method starting");
		try {
			String hql = "FROM Blog WHERE blog_status = 'Approved'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql); // creating a session object and Query
																				// object
			List<Blog> listApprovedBlogs = query.list(); // calling list() method of query object which returns a list
			if (listApprovedBlogs != null && !listApprovedBlogs.isEmpty()) {
				logger.info("List of approved Blog");
				return listApprovedBlogs;
			}

			logger.warn("list of approved blogs must be empty");
			return null;
		} catch (HibernateException e) {
			logger.info("Eror while displying list of approuved blog");
			e.printStackTrace();
			return null;
		}

	}
}
