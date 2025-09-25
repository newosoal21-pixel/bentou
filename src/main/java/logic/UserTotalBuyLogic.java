package logic;

import java.util.List;

import dao.OrderDAO;
import model.TotalBuy;

public class UserTotalBuyLogic {
    public List<TotalBuy> getMonthlyTotal(int year, int month) {
        try {
            OrderDAO dao = new OrderDAO();
            //指定した年月における、社員ごとの購入合計データを取得するメソッドの呼び出し
            return dao.getTotalBuyByMonth(year, month);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

