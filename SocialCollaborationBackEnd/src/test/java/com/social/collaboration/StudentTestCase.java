package com.social.collaboration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.social.collaboration.dao.StudentDAO;
import com.social.collaboration.model.Student;

public class StudentTestCase {

	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static StudentDAO studentDAO;

	@Autowired
	private static Student student;

	@BeforeClass
	public static void init() {
		// context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		context = new AnnotationConfigApplicationContext();
		context.scan("com.social");
		context.refresh();
		studentDAO = (StudentDAO) context.getBean("studentDAO");
		student = (Student) context.getBean("student");
	}

	/*
	 * @Test public void saveStudentTestCase() { Student student = new Student();
	 * student.setAddress1("Yamossoukro"); //student.setAddress2("Yamoussoukro");
	 * student.setAge(18); student.setDateReg("10/07/2018");
	 * student.setDob("01/01/2000"); student.setEmailID("s1@gmail.com");
	 * student.setFirst_name("SSS-1"); student.setGender("Female");
	 * //student.setStudentname("SSS-1"); // student.setStatus('N'); //
	 * student.setRole("Student"); student.setPassword("111");
	 * student.setMiddle_name("SSS"); student.setLast_name("SSS");
	 * student.setLast_seen("09/07/2018"); student.setMobile("987568989"); //
	 * student.setIsOnline('N'); student.setReligion("Christian");
	 * student.setAdmissionNO("AD001"); student.setSemester(1);
	 * //student.setStudentID(3000); student.setFname("FFF-1");
	 * student.setFnationality("Ivorian"); student.setFaddress("FAA-1");
	 * student.setFoccupation("Engineer"); student.setFnumber(99999999);
	 * student.setMaddress("MAA-1"); student.setMname("MMM-1");
	 * student.setMoccupation("Nurse"); student.setMnumber(99999999);
	 * student.setMnationality("Ivorian"); Boolean status =
	 * studentDAO.saveStudent(student); assertEquals("Save student", true, status);
	 * }
	 */

	/*
	 * @Test public void updateStudentTestCase() { Student student = new Student();
	 * student.setAddress1("Yamossoukro"); //student.setAddress2("Yamoussoukro");
	 * student.setAge(18); student.setDateReg("10/07/2018");
	 * student.setDob("01/01/2000"); student.setEmailID("s1@gmail.com");
	 * student.setFirst_name("SSS-1"); student.setGender("Female");
	 * //student.setStudentname("SSS-1"); // student.setStatus('N'); //
	 * student.setRole("Student"); student.setPassword("111");
	 * student.setMiddle_name("SSS"); student.setLast_name("SSS");
	 * student.setLast_seen("09/07/2018"); student.setMobile("987568989"); //
	 * student.setIsOnline('N'); student.setReligion("Christian");
	 * student.setAdmissionNO("AD001"); student.setSemester(1);
	 * student.setStudentID(3001); student.setFname("FFF-1");
	 * student.setFnationality("Ivorian"); student.setFaddress("FAA-1");
	 * student.setFoccupation("Engineer"); student.setFnumber(99999999);
	 * student.setMaddress("MAA-1"); student.setMname("MMM-1");
	 * student.setMoccupation("Nurse"); student.setMnumber(99999999);
	 * student.setMnationality("Ivorian"); Boolean status =
	 * studentDAO.updateStudent(student); assertEquals("Save student", true,
	 * status); }
	 */

	/*
	 * @Test public void getAllStudentTestCase() { List<Student> students =
	 * studentDAO.getAllStudent(); assertEquals("get all students", 4,
	 * students.size()); }
	 */

	/*
	 * @Test public void getStudentByEmailIDTestCase() {
	 * 
	 * Assert.assertNotNull("Get Student by emailID",
	 * studentDAO.getStudentByEmailID("s3@gmail.com")); }
	 */

	/*
	 * @Test public void getStudentByStudentnameTestCase() {
	 * 
	 * Assert.assertNotNull("Get Student by emailID",
	 * studentDAO.getStudentByStudentName("AAA-2")); }
	 */

	@Test
	public void deleteStudentSuccessTestCase() {
		boolean status = studentDAO.deleteStudentByID(3002);
		assertEquals("delete student succss test case", true, status);
	}

	/*
	 * @Test public void validateStudentTest() { Student status =
	 * studentDAO.studentValidation("AAA-2", "111");
	 * Assert.assertNotNull("Validate Student Test Case", status); }
	 */
}