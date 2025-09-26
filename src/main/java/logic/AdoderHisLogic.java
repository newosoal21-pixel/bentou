package logic;

import java.sql.SQLException;
import java.util.List;

import dao.OrderDAO;
import model.AdOrderHistory;

public class AdoderHisLogic {
	
	public List<AdOrderHistory> execute() throws SQLException{
		OrderDAO dao = new OrderDAO();
		List<AdOrderHistory> totalOrderDateList = dao.getTotalOrderDate();
		return totalOrderDateList;
	}
}
