package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeadlineDAO {

    // 締切時間を取得する
	//取得
	public String getDeadline() {
		//SQL(1件のみ取得)
		String sql = "SELECT DEADLINE FROM ORDER_DEADLINE LIMIT 1";
		// DBに接続 
		try (Connection conn = DBManager.getConnection(); // DBに接続
				PreparedStatement pStmt = conn.prepareStatement(sql)) { // SQLを準備

			// 実行して結果を取得
			try (ResultSet rs = pStmt.executeQuery()) {
				// レコードが1件でも存在すれば取り出す
				if (rs.next()) {
					return rs.getString("DEADLINE");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public boolean updateDeadline(String newTime) {
	    boolean success = false;
	    String sql = "UPDATE ORDER_DEADLINE SET DEADLINE_TIME = ?";

	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pStmt = conn.prepareStatement(sql)) {

	        // 例: "11:30" → "11:30:00"
	        pStmt.setString(1, newTime + ":00");  
	        int updated = pStmt.executeUpdate();
	        success = (updated > 0);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}
	
	public String getDeadlineStr() {

        String sql = "SELECT DEADLINE_TIME FROM ORDER_DEADLINE";
        String deadlineStr="";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

            if (rs.next()) {
                deadlineStr = rs.getString("DEADLINE_TIME");
            }

        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }

        return deadlineStr;
    }


}

