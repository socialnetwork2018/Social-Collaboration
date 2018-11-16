package com.social.collaboration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.social.collaboration.dao.ParentDAO;
import com.social.collaboration.model.Parent;

public class ParentTestCase {

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static ParentDAO parentDAO;

	@Autowired
	private static Parent parent;

	@BeforeClass
	public static void init() {
		// context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		context = new AnnotationConfigApplicationContext();
		context.scan("com.social");
		context.refresh();
		parentDAO = (ParentDAO) context.getBean("parentDAO");
		parent = (Parent) context.getBean("parent");
	}

	@Test
	public void saveParentTestCase() {
		Parent parent = new Parent();
		parent.setAddress1("Bouake");
		parent.setAddress2("Bouake");
		parent.setAge(40);
		parent.setDateReg("10/07/2018");
		parent.setDob("01/01/1978");
		parent.setEmailID("p1@gmail.com");
		parent.setFirst_name("PPP-1");
		parent.setGender("Female");
		parent.setParentname("PPP-1");
		// parent.setStatus('N');
		// parent.setRole("Parent"); 
		parent.setPassword("111");
		parent.setMiddle_name("PPP");
		parent.setLast_name("PPP");
		parent.setLast_seen("09/07/2018");
		parent.setMobile("987568989");
		// parent.setIsOnline('N'); 
		parent.setReligion("Christian");
		parent.setParentID(2000);
		parent.setOccupation("Nurse");
		parent.setMobile("99999999");
		parent.setPhoneNO("778995899");
		parent.setNationality("Ivorian");
		Boolean status = parentDAO.saveParent(parent);
		assertEquals("Save parent", true, status);
	}

	@Test
	public void updateParentTestCase() {
		Parent parent = new Parent();
		parent.setAddress1("Bouake");
		parent.setAddress2("Bouake");
		parent.setAge(40);
		parent.setDateReg("10/07/2018");
		parent.setDob("01/01/1978");
		parent.setEmailID("p0@gmail.com");
		parent.setFirst_name("PPP-0");
		parent.setGender("Female");
		parent.setParentname("PPP-0");
		// parent.setStatus('N');
		// parent.setRole("Parent"); 
		parent.setPassword("111");
		parent.setMiddle_name("PPP");
		parent.setLast_name("PPP");
		parent.setLast_seen("09/07/2018");
		parent.setMobile("987568989");
		parent.setIsOnline('N'); 
		parent.setReligion("Christian");
		parent.setParentID(2000);
		parent.setOccupation("Nurse");
		parent.setMobile("99999999");
		parent.setPhoneNO("778995899");
		parent.setNationality("Ivorian");
		Boolean status = parentDAO.updateParent(parent);
		assertEquals("Save parent", true, status);
	}


	

	/*
	 * @Test public void getAllParentTestCase() { List<Parent> parents =
	 * parentDAO.getAllParent(); assertEquals("get all parents", 4,
	 * parents.size()); }
	 */

	/*
	 * @Test public void getParentByEmailIDTestCase() {
	 * 
	 * Assert.assertNotNull("Get Parent by emailID",
	 * parentDAO.getParentByEmailID("a0@gmail.com")); }
	 */

	/*
	 * @Test public void getParentByParentnameTestCase() {
	 * 
	 * Assert.assertNotNull("Get Parent by emailID",
	 * parentDAO.getParentByParentName("AAA-2")); }
	 */

	/*
	 * @Test public void deleteParentSuccessTestCase() { boolean status =
	 * parentDAO.deleteParent("a1@gmail.com");
	 * assertEquals("delete parent succss test case", true, status); }
	 */

	/*
	 * @Test public void validateParentTest() { Parent status =
	 * parentDAO.parentValidation("AAA-2", "111");
	 * Assert.assertNotNull("Validate Parent Test Case", status); }
	 */
}