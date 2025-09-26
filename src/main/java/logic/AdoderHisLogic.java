package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DBManager;
import dao.OrderDAO;
import model.AdOrderHistory;

public class AdoderHisLogic {
	
	public List<AdOrderHistory> execute() throws SQLException{
		OrderDAO dao = new OrderDAO();
		List<AdOrderHistory> totalOrderDateList = dao.getTotalOrderDate();
		return totalOrderDateList;
	}
	
	public List<AdOrderHistory> executeByDate(String date) throws SQLException {
	    List<AdOrderHistory> list = new ArrayList<>();

	    String sql = "SELECT DATE(o.orderdate) AS orderDate, p.itemname AS itemName, "
	            + "SUM(o.quantity) AS totalQuantity, "
	            + "SUM(o.quantity * p.price) AS totalPrice "
	            + "FROM orders o "
	            + "JOIN products p ON o.products_id = p.products_id "
	            + "WHERE DATE(o.orderdate) = ? "
	            + "GROUP BY p.itemname, DATE(o.orderdate) "
	            + "ORDER BY DATE(o.orderdate) DESC";

	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, date);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                AdOrderHistory order = new AdOrderHistory();
	                order.setOrderDate(rs.getDate("orderDate").toLocalDate()); // ← alias 使用
	                order.setItemName(rs.getString("itemName"));              // ← alias 使用
	                order.setTotalQuantity(rs.getInt("totalQuantity"));
	                order.setTotalPrice(rs.getInt("totalPrice"));
	                list.add(order);
	            }
	        }
	    }

	    return list;
	}
}