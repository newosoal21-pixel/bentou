package model;

import java.io.Serializable;

public class PasswordChangeBean implements Serializable {
    private String nowPassword;
    private String changePass;
    private String comparisonPass;

    public String getNowPassword() {
        return nowPassword;
    }

    public void setNowPassword(String nowPassword) {
        this.nowPassword = nowPassword;
    }

    public String getChangePass() {
        return changePass;
    }

    public void setChangePass(String changePass) {
        this.changePass = changePass;
    }

    public String getComparisonPass() {
        return comparisonPass;
    }

    public void setComparisonPass(String comparisonPass) {
        this.comparisonPass = comparisonPass;
    }
}