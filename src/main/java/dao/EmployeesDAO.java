package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EmployeeBean;
import model.EmployeeEntry;

public class EmployeesDAO {
	
	public  EmployeeBean findByLogin(int employeesId, String password) {
		 String sql = "SELECT EMPLOYEES_ID, PASSWORD,USER_NAME,IS_ADMIN, DELETE_FLAG, EMPLOYEES.DEPARTMENT_ID,DEPARTMENT.DEPARTMENT_NAME "
		 		+ "FROM EMPLOYEES JOIN DEPARTMENT ON DEPARTMENT.DEPARTMENT_ID = EMPLOYEES.DEPARTMENT_ID WHERE EMPLOYEES_ID = ? AND PASSWORD = ? AND DELETE_FLAG=0";
		
		 	
		 try (Connection conn = DBManager.getConnection();   // DBに接続
	             PreparedStatement pStmt = conn.prepareStatement(sql)) { // SQLを準備
			 	pStmt.setInt(1, employeesId);
			 	pStmt.setString(2, password);
			 	
	            //SELECT文を 実行して結果表を取得
	            ResultSet rs = pStmt.executeQuery();
	                while(rs.next()) {
	                   int empId = rs.getInt("EMPLOYEES_ID");
	                   String pass = rs.getString("PASSWORD");
	                   String userName = rs.getString("USER_NAME");
	                   int isAdmin = rs.getInt("IS_ADMIN");
	                   boolean adminFlag = true;
	                   if(isAdmin==0) {
	                	   adminFlag = false;
	                   }
	                   //int deleteFlag = rs.getInt("DELETE_FLAG");
	                   int departmentId = rs.getInt("DEPARTMENT_ID");
	                   String departmentName = rs.getString("DEPARTMENT_NAME");
	                   return new EmployeeBean(empId, pass,userName,adminFlag,true,departmentId,departmentName);	                     	                
	            }    	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	     return null;
	
	}
    public boolean insert(EmployeeEntry entry) {
        String sql = "INSERT INTO EMPLOYEES (EMPLOYEE_ID, PASSWORD, USER_NAME, DEPARTMENT_ID) VALUES (?, ?, ?)";

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
    /**
     * ユーザー登録処理
     * @param employeeId 社員ID
     * @param password パスワード
     * @param userName ユーザー名
     * @param departmentId 部署ID
     * @return 登録成功ならtrue、失敗ならfalse
     */
    public boolean registerUser(int employeeId, String password, String userName, int departmentId) {
        String sql = "INSERT INTO EMPLOYEES (EMPLOYEES_ID, PASSWORD, USER_NAME,IS_ADMIN , DELETE_FLAG , DEPARTMENT_ID) "
        		+ "VALUES (?, ?, ?,0,0,?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);
            pstmt.setString(2, password);
            pstmt.setString(3, userName);
            pstmt.setInt(4, departmentId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }   

}
