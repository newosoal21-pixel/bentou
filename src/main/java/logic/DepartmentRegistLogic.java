package logic;

import java.util.List;

import dao.DepartmentDAO;
import model.DepartmentRegist;

public class DepartmentRegistLogic {
//部署IDから部署名を取得
public String getDepartmentName(int id) {
	DepartmentDAO dao = new DepartmentDAO();
	String departmentName = dao.findById(id);
	return departmentName;
}

//★★★ 部署リストを取得するメソッドを追加 ★★★
/**
 * 全部署のリストを取得する
 * @return 部署のリスト (List<DepartmentRegist>)
 */
public List<DepartmentRegist> getAllDepartments() {
    DepartmentDAO dao = new DepartmentDAO();
    // DAOのfindAll()を呼び出す
    return dao.findAll();
}
// ★★★ 追加終わり ★★★
}

