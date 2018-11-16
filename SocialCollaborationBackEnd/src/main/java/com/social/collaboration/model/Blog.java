package com.social.collaboration.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "c_blog")
public class Blog extends BaseDomain {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private int blog_id;

	private String blog_title; // TITLE MUST BE UNIQUE TO BE CHECKED LATER

	@Lob
	private String blog_description;
	private String username;
	private String blog_status; // not null. A->Accepted, N->new. R->reject

	private Date createdDate; //// Default SysteDate............
	// private String date_time;

	@Lob
	private String rejected;

	private int likes;
	private int unlikes;

	public int getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public String getBlog_title() {
		return blog_title;
	}

	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}

	public String getBlog_description() {
		return blog_description;
	}

	public void setBlog_description(String blog_description) {
		this.blog_description = blog_description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBlog_status() {
		return blog_status;
	}

	public void setBlog_status(String blog_status) {
		this.blog_status = blog_status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getRejected() {
		return rejected;
	}

	public void setRejected(String rejected) {
		this.rejected = rejected;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getUnlikes() {
		return unlikes;
	}

	public void setUnlikes(int unlikes) {
		this.unlikes = unlikes;
	}

}
