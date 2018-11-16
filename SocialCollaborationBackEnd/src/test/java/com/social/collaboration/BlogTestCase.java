package com.social.collaboration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.social.collaboration.dao.BlogDAO;
import com.social.collaboration.model.Blog;

public class BlogTestCase {

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static BlogDAO blogDAO;

	@Autowired
	private static Blog blog;

	@BeforeClass
	public static void init() {
		// context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		context = new AnnotationConfigApplicationContext();
		context.scan("com.social");
		context.refresh();
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
	}

	/*@Test
	public void saveBlogTestCase() {
		Blog blog = new Blog();
		blog.setBlog_description("This is AAA-3 blog");
		// blog.setBlog_id(100);
		blog.setUsername("AAA-4");
		blog.setBlog_title("AAA-4 blog");
		blog.setRejected("Rejected");
		Boolean status = blogDAO.addBlog(blog);
		assertEquals("Save blog", true, status);
	}
*/
	/*
	 * @Test public void updateBlogTest() { Blog blog = new Blog();
	 * blog.setBlog_description("My first blog"); blog.setBlog_id(100);
	 * blog.setUsername("AAA-2"); blog.setBlog_title("C++");
	 * blog.setRejected("Rejected"); Boolean status = blogDAO.updateBlog(blog);
	 * assertEquals("Update blog", true, status);
	 * //Assert.assertEquals("Add Blog Test Case", true, blogDAO.updateBlog(blog));
	 * }
	 */

	/*@Test
	public void getBlogByTitleTest() {
		assertNotNull(blogDAO.getBlogByTitle("C++"));
	}*/

	// @Ignore
	/*@Test
	public void approvesBlogTest() {
		blog = blogDAO.getBlogByTitle("Java");
		boolean status = blogDAO.approveBlog(blog);
		assertEquals("Approved blog", true, status);
	}*/
	
	/*@Test
	public void deleteBlogTest()
	{
		boolean status = blogDAO.deleteBlog(100);
		assertEquals("Delete Blog", true, status);
	}*/
	
	/*@Test
	public void getAllBlogTest()
	{
		List<Blog> blog = blogDAO.getAllBlogs();
		assertEquals("List of all blogs", 3, blog.size());
	}*/
	
	/*@Test
	public void getBlogByUserTest() {
		assertNotNull(blogDAO.getBlogByUser("AAA-0"));
	}*/
	
	@Test
	public void listAllApprovedBlogTest()
	{
		List<Blog> listApproved = blogDAO.getApprovedBlogs();
		assertTrue("List of approuved blog", listApproved.size()>0);
	}
	
}