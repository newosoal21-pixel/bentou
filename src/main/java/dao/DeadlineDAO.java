package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.OrderDeadline;

public class DeadlineDAO {

    // 締切時間を取得する
	public OrderDeadline getDeadline() {
        OrderDeadline deadline = null;

        String sql = "SELECT DEADLINE_TIME FROM ORDER_DEADLINE";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

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

