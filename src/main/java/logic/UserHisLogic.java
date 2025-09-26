package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.DBManager;
import model.EmployeeBean;
import model.EmployeeOrder;
import model.OrderBlock;
import model.OrderItem;

public class UserHisLogic {

    public static class MonthlySummary {
        private int totalPrice;
        private int totalCal;

        public MonthlySummary(int totalPrice, int totalCal) {
            this.totalPrice = totalPrice;
            this.totalCal = totalCal;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public int getTotalCal() {
            return totalCal;
        }
    }

    /* マイページに自分の今月の合計金額と総カロリーを取得 */
    public MonthlySummary getMonthlySummary(EmployeeBean employee) {
        int totalPrice = 0;
        int totalCal = 0;

        YearMonth currentMonth = YearMonth.now();
        LocalDate startDate = currentMonth.atDay(1);
        LocalDate endDate = currentMonth.atEndOfMonth();

        String sql = """
            SELECT o.quantity, p.price, p.cal
            FROM orders o
            INNER JOIN products p ON o.products_id = p.products_id
            WHERE o.employees_id = ?
              AND DATE(o.orderdate) BETWEEN ? AND ?
        """;

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employee.getEmployeesId());
            stmt.setDate(2, java.sql.Date.valueOf(startDate));
            stmt.setDate(3, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    int cal = rs.getInt("cal");

                    totalPrice += price * quantity;
                    totalCal += cal * quantity;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new MonthlySummary(0, 0); // エラー時は0で返す
        }

        return new MonthlySummary(totalPrice, totalCal);
    }
   
    /* 過去の注文履歴の取得（月ごと） */
    public List<EmployeeOrder> getMonthlyOrderHistory(EmployeeBean employee, String yearMonthStr) {
        List<EmployeeOrder> historyList = new ArrayList<>();

     
        YearMonth ym = YearMonth.parse(yearMonthStr);
        String startDate = ym.atDay(1).toString() + " 00:00:00";
        String endDate = ym.atEndOfMonth().toString() + " 23:59:59";

   
        String sql = """
            SELECT o.orderdate, o.products_id, p.itemname, o.quantity, p.price
            FROM orders o
            INNER JOIN products p ON o.products_id = p.products_id
            WHERE o.employees_id = ?
              AND o.orderdate BETWEEN ? AND ?
            ORDER BY o.orderdate DESC
        """;

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employee.getEmployeesId());
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDateTime orderDateTime = rs.getTimestamp("orderdate").toLocalDateTime();
                    int productId = rs.getInt("products_id");
                    String itemName = rs.getString("itemname");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    
                    int totalPrice = price * quantity; 

                    EmployeeOrder item = new EmployeeOrder(orderDateTime, quantity, totalPrice, productId);
                    item.setItemName(itemName); 
                    
                    historyList.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }

        return historyList;
    }

    /**
     * 当日分の注文を注文ID (orders_id) ごとにグループ化して取得する。
     * @param employee 従業員情報
     * @return 当日分の注文ブロックのリスト (List<OrderBlock>)
     */
    public List<OrderBlock> getTodayOrderBlocks(EmployeeBean employee) {
        // OrderBlockとOrderItemをインポートしてください
        // import model.OrderBlock;
        // import model.OrderItem;
        
        // LinkedHashMapを使って注文IDの順序を保持する
        Map<Integer, OrderBlock> orderBlocksMap = new java.util.LinkedHashMap<>();

        LocalDate today = LocalDate.now();
        
        // 当日 00:00:00 から 23:59:59 までの範囲を設定
        String startDate = today.toString() + " 00:00:00";
        String endDate = today.toString() + " 23:59:59";

        String sql = """
            SELECT o.orders_id, o.orderdate, p.itemname, o.quantity, p.price
            FROM orders o
            INNER JOIN products p ON o.products_id = p.products_id
            WHERE o.employees_id = ?
              AND o.orderdate BETWEEN ? AND ?
            ORDER BY o.orders_id ASC, o.orderdate ASC
        """;

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employee.getEmployeesId());
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int orderId = rs.getInt("orders_id");
                    LocalDateTime orderDateTime = rs.getTimestamp("orderdate").toLocalDateTime();
                    String itemName = rs.getString("itemname");
                    int quantity = rs.getInt("quantity");
                    int unitPrice = rs.getInt("price");
                    
                    OrderItem item = new OrderItem();
                    item.setOrderDateTime(orderDateTime);
                    item.setItemName(itemName);
                    item.setQuantity(quantity);
                    item.setUnitPrice(unitPrice); // 単価をセット

                    // 注文ID (orders_id) ごとにOrderBlockを作成または取得
                    OrderBlock block = orderBlocksMap.get(orderId);
                    if (block == null) {
                        block = new OrderBlock();
                        block.setOrderId(orderId);
                        block.setItems(new ArrayList<>());
                        orderBlocksMap.put(orderId, block);
                    }
                    
                    block.getItems().add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }

        // Mapの値をListに変換して返す（注文IDの昇順）
        return new ArrayList<>(orderBlocksMap.values());
    }
}