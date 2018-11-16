package com.social.collaboration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.social.collaboration.dao.UserDAO;
import com.social.collaboration.model.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static UserDAO userDAO;

	@Autowired
	private static User user;

	@BeforeClass
	public static void init() {
		// context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		context = new AnnotationConfigApplicationContext();
		context.scan("com.social");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		user = (User) context.getBean("user");
	}

	/*
	 * @Test public void saveUserTestCase() { User user = new User();
	 * user.setAddress1("AAA"); user.setAddress2("AAAA"); user.setAge(12);
	 * user.setDateReg("10/07/2018"); user.setDob("01/01/2000");
	 * user.setEmailID("a4@gmail.com"); user.setFirst_name("AAA");
	 * user.setGender("Female"); user.setUsername("AAA-4"); user.setStatus('N');
	 * user.setRole("User"); user.setPassword("111"); user.setMiddle_name("SSS");
	 * user.setLast_name("AAAA"); user.setLast_seen("09/07/2018");
	 * user.setMobile("987568989"); user.setIsOnline('N'); Boolean status =
	 * userDAO.saveUser(user); assertEquals("Save user", true, status); }
	 */

	/*
	 * @Test public void updateUserTest() { user.setAddress1("QQQQQQ");
	 * user.setAddress2("QQQQ"); user.setAge(12); user.setDateReg("10/07/2018");
	 * user.setDob("01/01/2000"); user.setEmailID("a0@gmail.com");
	 * user.setFirst_name("QQQ"); user.setGender("Female");
	 * user.setUsername("AAA-0"); user.setStatus('N'); user.setRole("User");
	 * user.setPassword("111"); user.setMiddle_name("SSS");
	 * user.setLast_name("AAAA"); user.setLast_seen("09/07/2018");
	 * user.setMobile("987568989"); user.setIsOnline('N'); Boolean status =
	 * userDAO.updateUser(user); assertEquals("Save user", true, status);
	 * Assert.assertEquals("Add User Test Case", true, userDAO.updateUser(user)); }
	 */
	/*
	 * @Test public void getAllUserTestCase() { List<User> users =
	 * userDAO.getAllUser(); assertEquals("get all users", 4, users.size()); }
	 */

	/*@Test
	public void getUserByEmailIDTestCase() {

		Assert.assertNotNull("Get User by emailID", userDAO.getUserByEmailID("a0@gmail.com"));
	}*/

	/*
	 * @Test public void getUserByUsernameTestCase() {
	 * 
	 * Assert.assertNotNull("Get User by emailID",
	 * userDAO.getUserByUserName("AAA-2")); }
	 */

	@Test
	public void deleteUserSuccessTestCase() {
		boolean status = userDAO.deleteUserByName("AAA-2");
		assertEquals("delete user succss test case", true, status);
	}

	/*
	 * @Test public void validateUserTest() { User status =
	 * userDAO.userValidation("AAA-2", "111");
	 * Assert.assertNotNull("Validate User Test Case", status); }
	 */
}