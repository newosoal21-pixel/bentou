package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.LoginHistory;

public class LoginhistoryDAO {
	public List<LoginHistory> getLoginHistoryDate(){
		List<LoginHistory> loginHisList = new ArrayList<>();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした", e);
        }
		
		String sql = "SELECT E.employees_id, "
				+ "       E.user_name, "
				+ "       CONVERT_TZ(L.login_time, '+00:00', '-09:00') AS login_time "
				+ "FROM login_history L "
				+ "JOIN employees E ON L.employees_id = E.employees_id "
				+ "ORDER BY L.login_time DESC";
		System.out.println(sql);
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

		
		try (Connection conn =DBManager.getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql);
                ResultSet rs = pStmt.executeQuery()) {
			
			while(rs.next()) {
				
				String userName = rs.getString("user_name");
				int employeesId = rs.getInt("employees_id");
				
				Timestamp ts = rs.getTimestamp("login_time");
                LocalDateTime loginTime = (ts != null) ? ts.toLocalDateTime() : null;
				
				LoginHistory a = new LoginHistory();
				a.setUserName(userName);
				a.setEmployeesId(employeesId);
				a.setLoginTime(loginTime);
				
				// フォーマットして文字列に
                String loginTimeStr = (loginTime != null) ? loginTime.format(formatter) : "";
                a.setLoginTimeStr(loginTimeStr);
				
                loginHisList.add(a);

			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
        return loginHisList;

	}
}
