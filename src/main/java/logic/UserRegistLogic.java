package logic;

import dao.EmployeesDAO;
import model.EmployeeEntry;

public class UserRegistLogic {

    private EmployeesDAO employeeDAO;

    public UserRegistLogic() {
        this.employeeDAO = new EmployeesDAO();
    }

    /**
     * バリデーションを実行し、問題がなければ登録処理を行う
     * @param employeeEntry ユーザー入力情報
     * @param passwordCheck 確認用パスワード
     * @return エラーメッセージ（問題なければnull）
     */
    public String validateAndRegister(EmployeeEntry employeeEntry, String passwordCheck) {
        // 全項目が入力されているか検証
        if (employeeEntry.getDepartmentId() == 0 || 
            employeeEntry.getUserName() == null || employeeEntry.getUserName().isEmpty() ||
            String.valueOf(employeeEntry.getEmployeesId()) == null || String.valueOf(employeeEntry.getEmployeesId()).isEmpty() ||
            employeeEntry.getPassword() == null || employeeEntry.getPassword().isEmpty() ||
            passwordCheck == null || passwordCheck.isEmpty()) {
            return "全ての項目を入力してください。";
        }

        // パスワードの長さ検証
        if (employeeEntry.getPassword().length() < 4) {
            return "パスワードは4文字以上で入力してください。";
        }

        // パスワードの一致検証
        if (!employeeEntry.getPassword().equals(passwordCheck)) {
            return "パスワードと確認用パスワードが一致しません。";
        }
        
        // DAOを呼び出してデータベースに登録
        try {
            // ここでは部署名をIDに変換する処理を省略します。
            // 実際には、departmentNameからdepartment_idを取得するDAOメソッドが必要です。
            int departmentId = 1; // ダミーの部署ID

            boolean isRegistered = employeeDAO.registerUser(
                employeeEntry.getEmployeesId(),
                employeeEntry.getPassword(),
                employeeEntry.getUserName(),
                employeeEntry.getDepartmentId()           
            );

            if (!isRegistered) {
                return "ユーザー登録に失敗しました。";
            }
            
            // 登録成功
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "登録中にエラーが発生しました。";
        }
    }
}
