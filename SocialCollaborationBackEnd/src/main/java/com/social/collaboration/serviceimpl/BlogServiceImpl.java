package com.social.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.social.collaboration.dao.BlogDAO;
import com.social.collaboration.model.Blog;
import com.social.collaboration.service.BlogService;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogDAO blogDAO;
	
	public boolean addBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDAO.addBlog(blog);
	}
	
	public List<Blog> getAllBlogs()
	{
		return blogDAO.getAllBlogs();
	}

	public Blog getBlogByTitle(String blog_title) {
		return blogDAO.getBlogByTitle(blog_title);
	}

	public List<Blog> getBlogByUser(String username) {
		return blogDAO.getBlogByUser(username);
	}

	public List<Blog> getApprovedBlogs() {
		return blogDAO.getApprovedBlogs();
	}
	

}
