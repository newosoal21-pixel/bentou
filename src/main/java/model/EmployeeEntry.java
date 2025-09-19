package model;

import java.io.Serializable;

public class EmployeeEntry implements Serializable {
	private int employeesId;
	private String userName;
	private String password;
	private String departmentName;
	private int departmentId;

	public EmployeeEntry() {}
	
	public EmployeeEntry(int employeesId, String userName, String password, String departmentName, int departmentId) {
		this.employeesId = employeesId;
		this.userName = userName;
		this.password = password;
		this.departmentName = departmentName;
		this.departmentId = departmentId;
	}

	public int getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(int employeesId) {
		this.employeesId = employeesId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "EmployeeEntry [employeesId=" + employeesId + ", userName=" + userName + ", password=" + password
				+ ", departmentId=" + departmentId + departmentId + "departmentName" + "]";
	}
	
	
	
}
