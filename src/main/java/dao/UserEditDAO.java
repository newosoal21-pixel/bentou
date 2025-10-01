package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserEditDAO {

    // DB接続情報（あなたの環境に合わせて変更）
   // private final String JDBC_URL = "jdbc:mysql://localhost:3306/lunchclerkDX?useSSL=false&serverTimezone=Asia/Tokyo";
   // private final String DB_USER = "root";
    //private final String DB_PASS = "root";

    /**
     * 名前と部署名を更新する
     */
	// 修正後（employeesIdを引数に追加）
	public boolean updateUser(String name, int departmentId, int employeesId) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DBManager.getConnection();
	        // SQL文もWHERE句を変更
	        String sql = "UPDATE EMPLOYEES SET user_name = ?, department_id = ? WHERE employees_id = ?";
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setString(1, name);
	        pstmt.setInt(2, departmentId);
	        pstmt.setInt(3, employeesId); // ログイン中のユーザーIDを設定

	        int rows = pstmt.executeUpdate();
	        return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
