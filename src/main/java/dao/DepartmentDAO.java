package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
	
	public List<Department> findByLogin() {
		 List<Department> empList = new ArrayList<>(); 
		 String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME WHERE DEPARTMENTID= ? AND DEPARTMENTNAME = ?";
		 
		 try (Connection conn = DBManager.getConnection();   
         PreparedStatement pStmt = conn.prepareStatement(sql)) { // SQLを準備
        	  
	            //SELECT文を 実行して結果表を取得
	            ResultSet rs = pStmt.executeQuery();
	                while(rs.next()) {
	                   String departmentid = rs.getString("DEPARTMENTID");
	                   String departmentname = rs.getString("DEPARTMENTNAME");
	                Department department = new Department(departmentid, departmentname);      
	            }    	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }

	        return empList;
	                        
		 
	}
}
