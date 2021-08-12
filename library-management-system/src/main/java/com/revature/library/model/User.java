package com.revature.library.model;



public class User {
	private int userId;
	private String userName;
	private String userRole;
	private String userMailId;
	private int userMobileNumber;
	private String userPassword;
	private String createdOn;
	
	 public User() {
		 
	 }
	
	
	
	public String getDateCreatedOn() {
		return createdOn;
	}



	public void setDateCreatedOn(String dateCreatedOn) {
		this.createdOn = dateCreatedOn;
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
		this.createdOn = dateCreatedOn;
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
				+ ", dateCreatedOn=" + createdOn + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userMailId == null) ? 0 : userMailId.hashCode());
		result = prime * result + userMobileNumber;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (userId != other.userId)
			return false;
		if (userMailId == null) {
			if (other.userMailId != null)
				return false;
		} else if (!userMailId.equals(other.userMailId))
			return false;
		if (userMobileNumber != other.userMobileNumber)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}
	
	
	
	

}
