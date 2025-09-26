package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LoginHistory implements Serializable {
	private String userName;
	private int employeesId;
    private int loginHistoryId;
    private LocalDateTime loginTime;
    
    // 表示用
    private String loginTimeStr;

    public String getUserName() {
    	return userName;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    public int getEmployeesId() {
    	return employeesId;
    }
    
    public void setEmployeesId(int employeesId) {
    	this.employeesId = employeesId;
    }
    
    public int getLoginHistoryID() {
        return loginHistoryId;
    }

    public void setLoginHistoryID(int loginHistoryId) {
        this.loginHistoryId = loginHistoryId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
    public String getLoginTimeStr() {
    	return loginTimeStr; 
    }
    public void setLoginTimeStr(String loginTimeStr) {
    	this.loginTimeStr = loginTimeStr; 
    }
    
}
