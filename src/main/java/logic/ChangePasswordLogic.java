package logic;

import java.util.HashMap;
import java.util.Map;

import dao.EmployeesDAO;

public class ChangePasswordLogic {

    private EmployeesDAO dao = new EmployeesDAO();


    public Map<String, String> validateAndChangePassword(String employeeId, String nowPassword,
                                                         String changePassword, String comparisonPassword) {
        Map<String, String> errors = new HashMap<>();

        // 現在のパスワードを取得
        String currentPassword = dao.getPasswordByEmployeeId(employeeId);
        System.out.println("DBのパスワード: [" + currentPassword + "]");
        System.out.println("入力パスワード: [" + nowPassword + "]");

        // 現在パスワードチェック
        if (currentPassword == null || !currentPassword.equals(nowPassword)) {
            errors.put("errorNow", "現在のパスワードが間違っています");
        }

        // 新しいパスワード長さチェック
        if (changePassword == null || changePassword.length() < 4) {
            errors.put("errorChange", "4文字以上で入力してください");
        }

        // 確認用パスワードチェック
        if (comparisonPassword == null || !comparisonPassword.equals(changePassword)) {
            errors.put("errorComparison", "確認用パスワードと一致しません");
        }

        // エラーがあれば変更せずに返す
        if (!errors.isEmpty()) {
            return errors;
        }

        // パスワード更新
        boolean updateResult = dao.updatePassword(employeeId, changePassword);

        if (!updateResult) {
            errors.put("errorChange", "パスワードの変更に失敗しました。再度お試しください。");
        }

        return errors;
    }
}
