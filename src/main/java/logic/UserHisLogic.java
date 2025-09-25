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

import dao.DBManager;
import model.EmployeeBean;
import model.EmployeeOrder;

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
}