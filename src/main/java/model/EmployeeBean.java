package model;

import java.io.Serializable;

public class EmployeeBean implements Serializable {
	private int employeesId;
	private String password;
	
	public EmployeeBean(int employeesId,String password){
		this.employeesId = employeesId;
		this.password = password;
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
	

}
