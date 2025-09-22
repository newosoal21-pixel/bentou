package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.OrderDeadline;

public class DeadlineDAO {

	private final String JDBC_URL="jdbc:mysql://localhost:3306/lunchclerkdx?useSSL=false&serverTimezone=UTC";
	private final String DB_USER="root";
	private final String DB_PASS="root";

    // 締切時間を取得する
    public OrderDeadline getDeadline() {
    	
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    }catch(ClassNotFoundException e) {
    	throw new IllegalStateException("JDBCドライバを読み込めませんでした");
    }
        OrderDeadline deadline = null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
        	String sql = "SELECT DEADLINE_TIME FROM ORDER_DEADLINE";
             PreparedStatement pStmt = conn.prepareStatement(sql);
            		

            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
               
                String deadlineStr = rs.getString("DEADLINE_TIME");
              

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime time = LocalTime.parse(deadlineStr, formatter);

                deadline = new OrderDeadline();
                deadline.setDeadlineTime(time);
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deadline;
    }


    // 締切時間を更新する
    public boolean updateDeadline(String newTime) {
        boolean success = false;
        String sql = "UPDATE ORDER_DEADLINE SET DEADLINE_TIME = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setString(1, newTime + ":00");  
            int updated = pStmt.executeUpdate();
            success = (updated > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}

