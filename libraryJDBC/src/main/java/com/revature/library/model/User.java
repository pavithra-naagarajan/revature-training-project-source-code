package com.revature.library.model;



public class User {
	private int userId;
	private String userName;
	private String userRole;
	private String userMailId;
	private int userMobileNumber;
	private String userPassword;
	private String dateCreatedOn;
	
	 public User() {
		 
	 }
	
	
	
	public String getDateCreatedOn() {
		return dateCreatedOn;
	}



	public void setDateCreatedOn(String dateCreatedOn) {
		this.dateCreatedOn = dateCreatedOn;
	}



	public User(int userId, String userName, String userRole, String userMailId, int userMobileNumber,
			String userPassword, String dateCreatedOn) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
		this.userMailId = userMailId;
		this.userMobileNumber = userMobileNumber;
		this.userPassword = userPassword;
		this.dateCreatedOn = dateCreatedOn;
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
	public int getUserMobileNumber() {
		return userMobileNumber;
	}
	public void setUserMobileNumber(int userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userRole=" + userRole + ", userMailId="
				+ userMailId + ", userMobileNumber=" + userMobileNumber + ", userPassword=" + userPassword
				+ ", dateCreatedOn=" + dateCreatedOn + "]";
	}
	
	
	
	

}
