package logic;

import dao.DepartmentDAO;

public class DepartmentRegistLogic {
//部署IDから部署名を取得
public String getDepartmentName(int id) {
	DepartmentDAO dao = new DepartmentDAO();
	String departmentName = dao.findById(id);
	return departmentName;
}
}
