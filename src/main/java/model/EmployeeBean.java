package model;

import java.io.Serializable;

public class EmployeeBean implements Serializable {
	private int employeesId;
	private String password;
	private String userName;
	private boolean isAdmin;
	private boolean deleteFlag;
	private int departmentId;
	private String departmentName;
	
	
	public EmployeeBean() {}
	public EmployeeBean(int employeesId, String password, String userName, boolean isAdmin, boolean deleteFlag,
			int departmentId,String departmentName) {

		this.employeesId = employeesId;
		this.password = password;
		this.userName = userName;
		this.isAdmin = isAdmin;	//trueの場合管理者
		this.deleteFlag = deleteFlag;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		
	}
	public int getEmployeesId() {
		return employeesId;
	}
	public void setEmployeesId(int employeesId) {
		this.employeesId = employeesId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	

	
	

}
