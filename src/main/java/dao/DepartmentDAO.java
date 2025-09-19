package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO {

	public String findById(int departmentId) {
		String departmentname="";
		String sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME FROM DEPARTMENT WHERE DEPARTMENT_ID= ? ";

		try (Connection conn = DBManager.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) { // SQLを準備
			pStmt.setInt(1, departmentId);

			//SELECT文を 実行して結果表を取得
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				departmentname = rs.getString("DEPARTMENT_NAME");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return departmentname;

	}
}
