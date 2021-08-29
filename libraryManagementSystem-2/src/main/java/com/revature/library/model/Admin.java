package com.revature.library.model;

public class Admin {
	
	private int adminId;
	private String adminName;
	private String adminRole;
	private String adminPassword;
	
	public Admin() {
		
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Admin(int adminId, String adminName, String adminRole, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminRole = adminRole;
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "AdminControl [adminId=" + adminId + ", adminName=" + adminName + ", adminRole=" + adminRole
				+ ", adminPassword=" + adminPassword + "]";
	}

	
}
