package com.social.collaboration.service;

import java.util.List;

import com.social.collaboration.model.Blog;

public interface BlogService {
	// Create new blog
	public boolean addBlog(Blog blog);
	
	//Get All blog
	public List<Blog> getAllBlogs();
	
	//Retrieve blog by the blog title
	public Blog getBlogByTitle(String blog_title);
	
	//Retrieve blog by the blog title
	public List<Blog> getBlogByUser(String username);
	
	//Retrieve all approved blogs
	public List<Blog> getApprovedBlogs();

}
