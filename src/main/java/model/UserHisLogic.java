package model;

import java.util.ArrayList;
import java.util.List;

import dao.OrderhistoryDAO;

public class UserHisLogic {
	public List<OrderHistoryBean> execute(){
		OrderhistoryDAO dao = new OrderhistoryDAO();
		List<OrderHistoryBean> orderhisList = dao.findByDetail();
		
		// nullチェックを追加
				if (orderhisList == null) {
					orderhisList = new ArrayList<>(); // 空のリストで初期化
				}

		return orderhisList;
	}
}
