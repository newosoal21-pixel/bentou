package model;

import java.io.Serializable;

public class EmployeeEntry implements Serializable {
	private int employeesId;
	private String userName;
	private String password;
	
	public EmployeeEntry(int employeesId,String userName,String password){
		this.employeesId = employeesId;
		this.userName = userName;
		this.password = password;	
	}

	public EmployeeEntry() {
		// TODO 自動生成されたコンストラクター・スタブ
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

	public void setDepartment(String parameter) {
		this.setDepartment(parameter);
		
	}

	public Object getDepartment() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
