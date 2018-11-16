package com.social.collaboration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component()
@Table(name = "c_student")
public class Student extends BaseDomain{
	
	@Id //studentID is a primary key
	private int studentID;

	
	//Null constraint
	@Column(unique = true , nullable = false)
    private	String emailID;
	
	//Null constraint
	@Column(unique = true , nullable = false)
    private String admissionNO;
	
    private String first_name;
    private String middle_name;
    private String last_name;
    private String gender;
    private String dob;
    private String password;
    private String dateReg;
    private String snationality;
    //private String subject; TO BE CHECKED LATER
    private String religion;
    private int semester;
    private Date batch; 
    //private int class_taken;
    //private char section;
    private int age;
    private String mobile;
    private String address1;
    //private String address2;
    private String role;
    private String  last_seen;
    private char isOnline;
    private char status;
    
    private String fname;
    private int fnumber;
    private String faddress;
    private String foccupation;
    private String fnationality;
    
    private String mname;
    private int mnumber;
    private String maddress;
    private String moccupation;
    private String mnationality;
	
	/*public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}*/
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateReg() {
		return dateReg;
	}
	public void setDateReg(String dateReg) {
		this.dateReg = dateReg;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/*public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}*/
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLast_seen() {
		return last_seen;
	}
	public void setLast_seen(String last_seen) {
		this.last_seen = last_seen;
	}
	public char getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	/*public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}*/
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	/*public char getSection() {
		return section;
	}
	public void setSection(char section) {
		this.section = section;
	}*/
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public Date getBatch() {
		return batch;
	}
	public void setBatch(Date batch) {
		this.batch = batch;
	}
	public String getAdmissionNO() {
		return admissionNO;
	}
	public void setAdmissionNO(String admissionNO) {
		this.admissionNO = admissionNO;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getFnumber() {
		return fnumber;
	}
	public void setFnumber(int fnumber) {
		this.fnumber = fnumber;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public String getFoccupation() {
		return foccupation;
	}
	public void setFoccupation(String foccupation) {
		this.foccupation = foccupation;
	}
	public String getFnationality() {
		return fnationality;
	}
	public void setFnationality(String fnationality) {
		this.fnationality = fnationality;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMnumber() {
		return mnumber;
	}
	public void setMnumber(int mnumber) {
		this.mnumber = mnumber;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public String getMoccupation() {
		return moccupation;
	}
	public void setMoccupation(String moccupation) {
		this.moccupation = moccupation;
	}
	public String getMnationality() {
		return mnationality;
	}
	public void setMnationality(String mnationality) {
		this.mnationality = mnationality;
	}
	public String getSnationality() {
		return snationality;
	}
	public void setSnationality(String snationality) {
		this.snationality = snationality;
	}
}
