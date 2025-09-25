package logic;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.OrderDAO;
import model.AdminOrder;

public class AdPreorderLogic {

    // 今日の注文一覧を取得
    public List<AdminOrder> execute() {
        OrderDAO dao = new OrderDAO();
        List<AdminOrder> orderList = dao.getOrdersByDate();
        return orderList;
    }

    // 今日の注文一覧を取得
    public Map<String, List<AdminOrder>> getDepartmentOrders() {
        OrderDAO dao = new OrderDAO();
        Map<String, List<AdminOrder>> map = dao.DepartmentOrders();
        return map;
    }
   // 今日の注文を発注済みに更新
    public int markTodayOrdersAsOrdered() {
        OrderDAO dao = new OrderDAO();
        try {
            return dao.markTodayOrdersAsOrdered(); // DAO に実装済みメソッド
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // 更新できなかった場合は0件
        }
    }
}