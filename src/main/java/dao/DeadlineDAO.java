package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeadlineDAO {
	//取得
	public String getDeadline() {
		//SQL(1件のみ取得)
		String sql = "SELECT DEADLINE_TIME FROM ORDER_DEADLINE LIMIT 1";
		// DBに接続 
		try (Connection conn = DBManager.getConnection(); // DBに接続
				PreparedStatement pStmt = conn.prepareStatement(sql)) { // SQLを準備

			// 実行して結果を取得
			try (ResultSet rs = pStmt.executeQuery()) {
				// レコードが1件でも存在すれば取り出す
				if (rs.next()) {
					return rs.getString("DEADLINE_TIME");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

}
