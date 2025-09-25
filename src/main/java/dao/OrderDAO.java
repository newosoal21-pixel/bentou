package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.AdminOrder;
import model.EmployeeBean;
import model.EmployeeOrder;

public class OrderDAO {

    // ----------------------------
    // 取得メソッド（新規追加）
    // ----------------------------
    public List<AdminOrder> getOrdersByDate() {
        List<AdminOrder> orderList = new ArrayList<>();
        Map<String, AdminOrder> map = new LinkedHashMap<>(); // 表示順保持

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした", e);
        }
        // order_flag の取得を追加
        String sql = "SELECT o.quantity, p.itemname, p.price, d.department_name, o.order_flag "
                   + "FROM orders AS o "
                   + "INNER JOIN products AS p ON o.products_id = p.products_id "
                   + "INNER JOIN employees AS e ON o.employees_id = e.employees_id "
                   + "INNER JOIN department AS d ON e.department_id = d.department_id "
                   + "WHERE DATE(o.orderdate) = CURDATE() "
                   + "ORDER BY o.orders_id DESC";
        System.out.println(sql);
        
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

            while (rs.next()) {
                String itemName = rs.getString("itemname");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String departmentName = rs.getString("department_name");
                int flag = rs.getInt("order_flag"); // ← 追加

                int totalPrice = quantity * price;

                if (map.containsKey(itemName)) {
                    AdminOrder existing = map.get(itemName);
                    existing.setTotalQuantity(existing.getTotalQuantity() + quantity);
                    existing.setTotalPrice(existing.getTotalPrice() + totalPrice);
                    // 発注フラグは「全て発注済みなら1」。AND相当のロジックにする
                    existing.setOrderFlag(existing.getOrderFlag() & flag);
                    
                } else {
                    AdminOrder a = new AdminOrder(itemName, quantity, totalPrice, departmentName);
                    a.setOrderFlag(flag);
                    System.out.println(a);
                    map.put(itemName, a);
                }
            }

            orderList.addAll(map.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return orderList;
    }
    public int markTodayOrdersAsOrdered() throws SQLException {
        String sql = "UPDATE orders SET order_flag = 1 WHERE DATE(orderdate) = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new Date(System.currentTimeMillis()));

            return stmt.executeUpdate(); // 更新件数を返す
        }
    }
    
    //発注済か確認する
    //true 発注済
    //false 未発注
    public boolean isOrdered() {
    	boolean result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした", e);
        }

        // order_flagが0の注文のみ取得
        String sql = "SELECT * "
                   + "FROM orders "
                  + "WHERE orderdate = CURDATE() ";


        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

            if(rs.next()) {
               
                int orderFlag = rs.getInt("order_flag");
                if(orderFlag==1) {
                	result = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // ----------------------------
    // 保存メソッド（オーダーをDBに保存）
    // ----------------------------
    
    public void saveOrders(List<EmployeeOrder> orderList, EmployeeBean employee) throws SQLException {
        String sql = "INSERT INTO orders (employees_id, quantity, orderdate, products_id, order_flag) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBManager.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql);

            
            int savedCount = 0;
            for (EmployeeOrder order : orderList) {
                stmt.setInt(1, employee.getEmployeesId());
                stmt.setInt(2, order.getTotalQuantity());
                stmt.setTimestamp(3, Timestamp.valueOf(order.getDateTime()));
                stmt.setInt(4, order.getProductId());
                stmt.setInt(5, 0);

                stmt.executeUpdate();
                savedCount++;
            }

            conn.commit();
            System.out.println("注文情報を保存しました。保存件数: " + savedCount);
           
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("保存失敗。ロールバックしました。");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e;

        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }
    }
    
    
    
}