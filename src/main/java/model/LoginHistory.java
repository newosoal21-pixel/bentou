package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LoginHistory implements Serializable {
    private int loginHistoryID;
    private LocalDateTime loginTime;

    public int getLoginHistoryID() {
        return loginHistoryID;
    }

    public void setLoginHistoryID(int loginHistoryID) {
        this.loginHistoryID = loginHistoryID;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
