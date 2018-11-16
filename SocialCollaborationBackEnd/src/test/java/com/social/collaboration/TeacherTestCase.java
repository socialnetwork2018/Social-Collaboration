package com.social.collaboration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.social.collaboration.dao.TeacherDAO;
import com.social.collaboration.model.Teacher;

public class TeacherTestCase {

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static TeacherDAO teacherDAO;

	@Autowired
	private static Teacher teacher;

	@BeforeClass
	public static void init() {
		// context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		context = new AnnotationConfigApplicationContext();
		context.scan("com.social");
		context.refresh();
		teacherDAO = (TeacherDAO) context.getBean("teacherDAO");
		teacher = (Teacher) context.getBean("teacher");
	}

	/*
	 * @Test public void saveTeacherTestCase() { Teacher teacher = new Teacher();
	 * teacher.setAddress1("Abidjan"); teacher.setAddress2("Abidjan");
	 * teacher.setAge(28); teacher.setDateReg("10/07/2018");
	 * teacher.setDob("01/01/1990"); teacher.setEmailID("t2@gmail.com");
	 * teacher.setFirst_name("TTT-1"); teacher.setGender("Female");
	 * teacher.setTeachername("TTT-2"); //teacher.setStatus('N');
	 * //teacher.setRole("Teacher"); teacher.setPassword("111");
	 * teacher.setMiddle_name("SSS"); teacher.setLast_name("TTTT");
	 * teacher.setLast_seen("09/07/2018"); teacher.setMobile("987568989");
	 * //teacher.setIsOnline('N'); teacher.setSubject("Math");
	 * teacher.setClass_taken(1); teacher.setSection('A');
	 * teacher.setReligion("Christian"); //teacher.setTeacherID(2000); Boolean
	 * status = teacherDAO.saveTeacher(teacher); assertEquals("Save teacher", true,
	 * status); }
	 */

	@Test
	public void updateTeacherTest() {
		teacher.setAddress1("Abidjan"); 
		teacher.setAddress2("Abidjan");
		teacher.setAge(28); 
		 teacher.setDateReg("10/07/2018");
		 teacher.setDob("01/01/1990"); 
		 teacher.setEmailID("t0@gmail.com");
		  teacher.setFirst_name("TTT-1"); 
		 teacher.setGender("Female");
		  teacher.setTeachername("TTT-0"); 
		 teacher.setStatus('N');
		 teacher.setRole("Teacher"); 
		 teacher.setPassword("111");
		  teacher.setMiddle_name("SSS"); 
		 teacher.setLast_name("TTTT");
		  teacher.setLast_seen("09/07/2018"); 
		 teacher.setMobile("987568989");
		  teacher.setIsOnline('N'); 
		 teacher.setSubject("French");
		  teacher.setClass_taken(1); 
		 teacher.setSection('B');
		  teacher.setReligion("Christian"); 
		 teacher.setTeacherID(2000);
		Boolean status = teacherDAO.updateTeacher(teacher);
		assertEquals("Save teacher", true, status);
		Assert.assertEquals("Add Teacher Test Case", true, teacherDAO.updateTeacher(teacher));
	}

	/*
	 * @Test public void getAllTeacherTestCase() { List<Teacher> teachers =
	 * teacherDAO.getAllTeacher(); assertEquals("get all teachers", 4,
	 * teachers.size()); }
	 */

	/*
	 * @Test public void getTeacherByEmailIDTestCase() {
	 * 
	 * Assert.assertNotNull("Get Teacher by emailID",
	 * teacherDAO.getTeacherByEmailID("a0@gmail.com")); }
	 */

	/*
	 * @Test public void getTeacherByTeachernameTestCase() {
	 * 
	 * Assert.assertNotNull("Get Teacher by emailID",
	 * teacherDAO.getTeacherByTeacherName("AAA-2")); }
	 */

	/*
	 * @Test public void deleteTeacherSuccessTestCase() { boolean status =
	 * teacherDAO.deleteTeacher("a1@gmail.com");
	 * assertEquals("delete teacher succss test case", true, status); }
	 */

	/*
	 * @Test public void validateTeacherTest() { Teacher status =
	 * teacherDAO.teacherValidation("AAA-2", "111");
	 * Assert.assertNotNull("Validate Teacher Test Case", status); }
	 */
}