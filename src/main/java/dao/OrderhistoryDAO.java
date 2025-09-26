package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderHistoryBean;

public class OrderhistoryDAO {
	public List<OrderHistoryBean> findByDetail() {
		 List<OrderHistoryBean> orderhisList = new ArrayList<>();
		    String sql = "SELECT * FROM ORDERS";

		    try (Connection conn = DBManager.getConnection();
		         PreparedStatement pStmt = conn.prepareStatement(sql);
		         ResultSet rs = pStmt.executeQuery()) {

		        while (rs.next()) {
		            OrderHistoryBean orderhis = new OrderHistoryBean(
		                rs.getInt("orders_id"),
		                rs.getString("itemname"),
		                rs.getInt("price"),
	                    rs.getString("image"),
	                    rs.getInt("quantity")
		            );
		            orderhisList.add(orderhis);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;

	}
			return orderhisList;

}
	
}
