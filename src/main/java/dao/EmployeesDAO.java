package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EmployeeBean;
import model.EmployeesList;

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
  //指定された社員IDに該当する社員情報をデータベースから1件取得する処理//
    public EmployeesList findById(int employeeId) {
        String sql = "SELECT EMPLOYEES_ID, USER_NAME FROM EMPLOYEES WHERE EMPLOYEES_ID = ? AND DELETE_FLAG = 0";
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pStmt = conn.prepareStatement(sql)) {
	
	    	pStmt.setInt(1, employeeId);
	        ResultSet rs = pStmt.executeQuery();
	
	        //next()はデータが1件でもあれば true、なければ falseを返す//
	        if (rs.next()) {
	            EmployeesList emp = new EmployeesList();
	            emp.setEmployeesId(rs.getInt("EMPLOYEES_ID"));
	            emp.setUserName(rs.getString("USER_NAME"));
	            return emp;
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//「全社員の一覧」をデータベースから取得して、リストとして返す//
    public List<EmployeesList> findAll() {
        List<EmployeesList> list = new ArrayList<>();
        String sql = "SELECT EMPLOYEES_ID, USER_NAME FROM EMPLOYEES WHERE DELETE_FLAG = 0 ORDER BY EMPLOYEES_ID";
	
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pStmt = conn.prepareStatement(sql);
	         ResultSet rs = pStmt.executeQuery()) {
	
	        while (rs.next()) {
	            EmployeesList emp = new EmployeesList();
	            emp.setEmployeesId(rs.getInt("EMPLOYEES_ID"));
	            emp.setUserName(rs.getString("USER_NAME"));
	            list.add(emp);
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return list;
	}

	//データーベースから該当の社員を削除//
	public boolean deleteById(int employeeId) {
	    String sql = "UPDATE EMPLOYEES SET DELETE_FLAG = 1 WHERE EMPLOYEES_ID = ?";

	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pStmt = conn.prepareStatement(sql)) {

	        pStmt.setInt(1, employeeId);
	        int rowsUpdated = pStmt.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//EmployeeIDからpasswardを取得するメソッド//
    public String getPasswordByEmployeeId(String employeeId) {
        String password = null;

        String sql = "SELECT PASSWORD FROM EMPLOYEES  WHERE EMPLOYEES_ID = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setString(1, employeeId);
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                password = rs.getString("PASSWORD");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return password;
    }
    
   //新しいパスワードをデータベースに登録する//
    public boolean updatePassword(String employeeId, String changePassword) {
        String sql = "UPDATE EMPLOYEES SET PASSWORD = ? WHERE EMPLOYEES_ID = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setString(1, changePassword);
            pStmt.setString(2, employeeId);

            int rowsAffected = pStmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}





