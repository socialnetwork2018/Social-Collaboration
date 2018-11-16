package com.social.collaboration.dao;

import java.util.List;

import com.social.collaboration.model.Blog;

public interface BlogDAO {

	// Create new blog
	public boolean addBlog(Blog blog);
	
	// to approve A blog by ADMIN only/accept/reject.
	public boolean approveBlog(Blog blog);

	public boolean updateBlog(Blog blog);

	public boolean deleteBlog(int blog_id);
	
	// DELETE BLOG BY BLOG TITLE TO BE DECLARED LATER

	public Blog getBlogByTitle(String blog_title);

	//Get a blog by user name
	public List<Blog> getBlogByUser(String username);
	
	//Get a blog by blog id
	public List<Blog> getBlogByID(String blog_id);

	//List of approved blog
	public List<Blog> getApprovedBlogs();

	public List<Blog> getAllBlogs();

}
