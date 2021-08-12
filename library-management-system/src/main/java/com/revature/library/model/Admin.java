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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminId;
		result = prime * result + ((adminName == null) ? 0 : adminName.hashCode());
		result = prime * result + ((adminPassword == null) ? 0 : adminPassword.hashCode());
		result = prime * result + ((adminRole == null) ? 0 : adminRole.hashCode());
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
		Admin other = (Admin) obj;
		if (adminId != other.adminId)
			return false;
		if (adminName == null) {
			if (other.adminName != null)
				return false;
		} else if (!adminName.equals(other.adminName))
			return false;
		if (adminPassword == null) {
			if (other.adminPassword != null)
				return false;
		} else if (!adminPassword.equals(other.adminPassword))
			return false;
		if (adminRole == null) {
			if (other.adminRole != null)
				return false;
		} else if (!adminRole.equals(other.adminRole))
			return false;
		return true;
	}
	
}
