package logic;

import dao.EmployeesDAO;
import model.EmployeeBean;

public class LoginLogic {

    /**
     * ユーザーIDとパスワードの認証を行い、管理者の場合はtrue、一般ユーザーの場合はfalseを返す。
     * @param employeesId ユーザーID
     * @param password パスワード
     * @return 認証成功時はtrue。認証失敗時はnull。
     */
    public EmployeeBean execute(int employeesId, String password) {
        EmployeesDAO employeeDAO = new EmployeesDAO();
        return employeeDAO.findByLogin( employeesId, password);
    }
    
 
}
