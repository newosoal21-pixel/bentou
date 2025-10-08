package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DepartmentRegist;

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
	
	public List<DepartmentRegist> findAll() { // ★戻り値を DepartmentRegist に変更
		List<DepartmentRegist> departmentList = new ArrayList<>();
		String sql = "SELECT department_id, department_name FROM department ORDER BY department_id";

		try (Connection conn = DBManager.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("department_id");
				String name = rs.getString("department_name");
				// ★修正点: DepartmentRegistの新しいコンストラクタを使用
				departmentList.add(new DepartmentRegist(id, name)); 
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return departmentList;
	}
	
	// ★部署名を登録するメソッド
		public boolean insert(String departmentName) {
			// department_id がオートインクリメントであることを前提とします
			String sql = "INSERT INTO department (department_name) VALUES (?)";

			try (Connection conn = DBManager.getConnection();
					PreparedStatement pStmt = conn.prepareStatement(sql)) {

				pStmt.setString(1, departmentName);

				int result = pStmt.executeUpdate();
				return result == 1; // 1行登録されれば成功

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		// ★部署名を変更するメソッド (後続の処理に必要)
		public boolean update(int departmentId, String newDepartmentName) {
			String sql = "UPDATE department SET department_name = ? WHERE department_id = ?";
			
			try (Connection conn = DBManager.getConnection();
				 PreparedStatement pStmt = conn.prepareStatement(sql)) {
				
				pStmt.setString(1, newDepartmentName);
				pStmt.setInt(2, departmentId);
				
				int result = pStmt.executeUpdate();
				return result == 1;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
