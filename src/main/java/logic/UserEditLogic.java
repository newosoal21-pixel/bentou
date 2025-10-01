package logic;

import dao.UserEditDAO;

public class UserEditLogic {
    // 修正後（employeesIdを引数に追加）
    public boolean execute(String name, int departmentId, int employeesId) {
        UserEditDAO dao = new UserEditDAO();
        return dao.updateUser(name, departmentId, employeesId);
    }
    
 // 部署名を取得する新しいメソッドを追加
    public String getDepartmentName(int departmentId) {
        UserEditDAO dao = new UserEditDAO();
        return dao.findDepartmentNameById(departmentId);
    }
}


