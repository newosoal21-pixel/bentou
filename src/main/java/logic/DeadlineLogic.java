package logic;

import dao.DeadlineDAO;

public class DeadlineLogic {
	DeadlineDAO dao = new DeadlineDAO();

	public String getDeadline(){	
		return dao.getDeadline();
	}
}
