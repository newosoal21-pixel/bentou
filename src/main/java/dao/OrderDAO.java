package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.AdminOrder;
import model.OrderDate;
import model.TotalBuy;

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
    //「指定された年と月に、社員ごとに合計いくら使ったか」を集計するメソッド
    public List<TotalBuy> getTotalBuyByMonth(int year,int month)
    		throws SQLException {
        List<TotalBuy> totalList = new ArrayList<>();

        String sql = 
        	    "SELECT E.employees_id, E.user_name, SUM(O.quantity * I.price) AS total " +//表示したい項目の選択、社員IDと名前とトータルの金額、それにtotalという別名をつける
        	    "FROM employees E " +
        	    "JOIN orders O ON E.employees_id = O.employees_id " +//社員IDでordersテーブルと結びつける
        	    "JOIN products I ON O.products_id = I.products_id "+//item_idでitemsテーブルと結びつける
        	    "WHERE YEAR(O.orderdate) = ? AND MONTH(O.orderdate) = ? " +//注文日の年と月が、指定された年・月と一致する行だけを対象（YEAR,MONTHは日付データから「年」や「月」だけを取り出して使う関数)
        	    "GROUP BY E.employees_id, E.user_name " +//社員IDと名前でグループ化する
        	    "ORDER BY E.employees_id ";//社員IDの昇順


        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

        	pStmt.setInt(1, year);
        	pStmt.setInt(2, month);

            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    TotalBuy totalBuy = new TotalBuy();
                    totalBuy.setEmployeesId(rs.getInt("employees_id"));
                    totalBuy.setUserName(rs.getString("user_name"));
                    totalBuy.setTotalBuy(rs.getInt("total"));
                    totalList.add(totalBuy);
                    System.out.println(totalBuy);
                }
            }
        }

        return totalList; //戻り値は TotalBuy（社員ID・名前・合計金額）のリスト
}
    //「注文された年と月の組み合わせをすべて取得する」ためのメソッド
    public List<OrderDate> getOrderDates() throws SQLException {
        List<OrderDate> dateList = new ArrayList<>();

        String sql = "SELECT DISTINCT YEAR(orderdate) AS year, MONTH(orderdate) AS month " +
                     "FROM orders " +
                     "ORDER BY year DESC, month DESC";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

            while (rs.next()) {
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                dateList.add(new OrderDate(year, month));
            }
        }

        return dateList;
    }
    
    public List<AdminOrder> DepartmentOrders() throws SQLException{
    	List<AdminOrder> orderList = new ArrayList<>();
    	Map<String, AdminOrder> map = new LinkedHashMap<>();
    	
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした", e);
        }
    	
        String sql = "SELECT "
        		+ " d.department_name,p.itemname, "
        		+ " SUM(o.quantity) AS total_quantity, "
        		+ " SUM(o.quantity * p.price) AS total_price, "
        		+ " MAX(o.order_flag) AS order_flag "
        		+ "FROM orders AS o "
        		+ "INNER JOIN products AS p ON o.products_id = p.products_id "
        		+ "INNER JOIN employees AS e ON o.employees_id = e.employees_id "
        		+ "INNER JOIN department AS d ON e.department_id = d.department_id "
        		+ "WHERE DATE(o.orderdate) = CURDATE() "
        		+ "GROUP BY d.department_id, d.department_name,p.itemname ,p.products_id "
        		+ "ORDER BY d.department_id;";
        System.out.println(sql);
        
        try (Connection conn =DBManager.getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql);
                ResultSet rs = pStmt.executeQuery()) {
        	
        	while (rs.next()) {
                String itemName = rs.getString("itemname");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String departmentName = rs.getString("department_name");
                int flag = rs.getInt("order_flag"); 
                
                int totalPrice = quantity * price;

                if (map.containsKey(departmentName)) {
                    AdminOrder a = map.get(departmentName);
                    a.setItemName(a.getItemName());
                    a.setTotalQuantity(a.getTotalQuantity() + quantity);
                    a.setTotalPrice(a.getTotalPrice() + totalPrice);
                                        
                } else {
                    AdminOrder a = new AdminOrder(departmentName, quantity, totalPrice, itemName);
                    a.setOrderFlag(flag);
                    System.out.println(a);
                    map.put(departmentName, a);
                }
            }

            orderList.addAll(map.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return orderList;
    
}
}

    
