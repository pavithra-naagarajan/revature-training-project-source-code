package com.revature.library.model;


import java.util.Date;
import java.util.Objects;

public class User {
	private int userId;
	private String userName;
	private String userRole;
	private String userMailId;
	private String userMobileNumber;
	private String userPassword;
	private Date createdOn;
	
	 public User() {
		 
	 }

	public User(int userId, String userName, String userRole, String userMailId, String userMobileNumber,
			String userPassword, Date createdOn) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
		this.userMailId = userMailId;
		this.userMobileNumber = userMobileNumber;
		this.userPassword = userPassword;
		this.createdOn = createdOn;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserMailId() {
		return userMailId;
	}

	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn( Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userRole=" + userRole + ", userMailId="
				+ userMailId + ", userMobileNumber=" + userMobileNumber + ", userPassword=" + userPassword
				+ ", createdOn=" + createdOn + "]";
	}

	
	
	
	

}
