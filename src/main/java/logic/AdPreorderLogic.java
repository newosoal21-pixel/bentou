package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
        Map<String, List<AdminOrder>> map = dao.getDepartmentOrders();
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
    /**
     * 部署別マップから総合集計リストを再構築する（発注完了後の画面表示用）
     */
    public List<AdminOrder> getOrdersFromMap(Map<String, List<AdminOrder>> departmentMap) {
        if (departmentMap == null || departmentMap.isEmpty()) {
            return new ArrayList<>();
        }
        
        // アイテム名をキーにして、個数と金額を合算するためのマップ
        Map<String, AdminOrder> combinedMap = new HashMap<>(); 
        
        for (List<AdminOrder> orders : departmentMap.values()) {
            for (AdminOrder order : orders) {
                String itemName = order.getItemName();
                if (combinedMap.containsKey(itemName)) {
                    AdminOrder existing = combinedMap.get(itemName);
                    existing.setTotalQuantity(existing.getTotalQuantity() + order.getTotalQuantity());
                    existing.setTotalPrice(existing.getTotalPrice() + order.getTotalPrice());
                } else {
                    // 新しいAdminOrderインスタンスを作成して追加
                    AdminOrder newOrder = new AdminOrder();
                    newOrder.setItemName(order.getItemName());
                    newOrder.setTotalQuantity(order.getTotalQuantity());
                    newOrder.setTotalPrice(order.getTotalPrice());
                    combinedMap.put(itemName, newOrder);
                }
            }
        }
        // マップの値をリストにして返す
        return new ArrayList<>(combinedMap.values());
    }
}