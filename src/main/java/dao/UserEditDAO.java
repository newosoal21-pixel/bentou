package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEditDAO {

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
	
    /* 部署IDに基づいて部署名を取得する
    * @param departmentId 部署ID
    * @return 部署名 (見つからない場合はnull)
    */
   public String findDepartmentNameById(int departmentId) {
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       String departmentName = null;

       try {
           conn = DBManager.getConnection();
           
           // DEPARTMENTSテーブルから部署名を取得するSQL
           String sql = "SELECT department_name FROM DEPARTMENT WHERE department_id = ?";
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, departmentId);

           rs = pstmt.executeQuery();

           if (rs.next()) {
               // 結果が取得できたら部署名をセット
               departmentName = rs.getString("department_name");
           }
           // else 部署名が見つからなかった場合、departmentName は null のまま

       } catch (SQLException e) {
           e.printStackTrace();
       } catch (Exception e) {
            e.printStackTrace(); // DBManager.getConnection()などで発生する可能性のある例外
       } finally {
           // リソースのクローズ処理
           try { if (rs != null) rs.close(); } catch (SQLException e) {}
           try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
           try { if (conn != null) conn.close(); } catch (SQLException e) {}
       }
       return departmentName;
   }
}
