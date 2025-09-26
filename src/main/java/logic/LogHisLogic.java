package logic;

import java.util.List;

import dao.LoginhistoryDAO;
import model.LoginHistory;

public class LogHisLogic {
	
	public List<LoginHistory> execute(){
		LoginhistoryDAO dao = new LoginhistoryDAO();
		List<LoginHistory> loginHisList = dao.getLoginHistoryDate();
		return loginHisList;
	}
}
