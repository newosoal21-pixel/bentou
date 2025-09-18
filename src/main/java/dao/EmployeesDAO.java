package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EmployeeBean;
import model.EmployeeEntry;

public class EmployeesDAO {
	
	public  EmployeeBean findByLogin(int employeesId, String password) {
		 String sql = "SELECT EMPLOYEES_ID, PASSWORD FROM EMPLOYEES WHERE EMPLOYEES_ID = ? AND PASSWORD = ?";
		
		 	
		 try (Connection conn = DBManager.getConnection();   // DBに接続
	             PreparedStatement pStmt = conn.prepareStatement(sql)) { // SQLを準備
			 	pStmt.setInt(1, employeesId);
			 	pStmt.setString(2, password);
			 	
	            //SELECT文を 実行して結果表を取得
	            ResultSet rs = pStmt.executeQuery();
	                while(rs.next()) {
	                   int empId = rs.getInt("EMPLOYEES_ID");
	                   String pass = rs.getString("PASSWORD");
	                   return new EmployeeBean(empId, pass);	                     	                
	            }    	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	     return null;
	
	}
    public boolean insert(EmployeeEntry entry) {
        String sql = "INSERT INTO EMPLOYEES (EMPLOYEESID, PASSWORD, USERNAME, DEPARTMENTID) VALUES (?, ?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
        	
        	pStmt.setInt(1, entry.getEmployeesId());
        	pStmt.setString(2, entry.getPassword());
        	pStmt.setString(3, entry.getUserName());
                                 

            int result = pStmt.executeUpdate();

            return result > 0;  // 1件以上登録できたらtrue

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	public Boolean checkLoginAndGetAdminStatus(int employeesId, String password) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	public boolean registerUser(int employeesId, String password, String userName, int departmentId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
