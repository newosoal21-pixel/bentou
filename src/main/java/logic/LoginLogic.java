package logic;

import dao.EmployeesDAO;

public class LoginLogic {

    /**
     * ユーザーIDとパスワードの認証を行い、管理者の場合はtrue、一般ユーザーの場合はfalseを返す。
     * @param employeesId ユーザーID
     * @param password パスワード
     * @return 認証成功時は管理者の場合はtrue、一般ユーザーの場合はfalse。認証失敗時はnull。
     */
    public Boolean execute(int employeesId, String password) {
        EmployeesDAO employeeDAO = new EmployeesDAO();
        return employeeDAO.checkLoginAndGetAdminStatus(employeesId, password);
    }
}
