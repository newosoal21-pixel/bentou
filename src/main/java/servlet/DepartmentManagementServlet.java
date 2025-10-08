package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import model.DepartmentRegist;

@WebServlet("/DepartmentManagementServlet")
public class DepartmentManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JSP表示処理（一覧取得）
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DepartmentDAO dao = new DepartmentDAO();
		List<DepartmentRegist> departmentList = dao.findAll();
		
		request.setAttribute("departmentList", departmentList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/departmentreg.jsp");
		dispatcher.forward(request, response);
	}

	// 登録/変更処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String actionType = request.getParameter("actionType");
		DepartmentDAO dao = new DepartmentDAO();
		String message = "";
		
		if ("insert".equals(actionType)) {
			// 新規登録処理
			String newDeptName = request.getParameter("deptName");
			if (newDeptName != null && !newDeptName.trim().isEmpty()) {
				if (dao.insert(newDeptName)) {
					message = "「" + newDeptName + "」を新規登録しました。";
				} else {
					message = "登録に失敗しました。";
				}
			} else {
				message = "部署名が入力されていません。";
			}
		} else if ("update".equals(actionType)) {
			// 変更処理
			String deptIdStr = request.getParameter("deptId");
			String updatedName = request.getParameter("updatedDeptName");
			
			if (deptIdStr != null && updatedName != null) {
				int deptId = Integer.parseInt(deptIdStr);
				if (dao.update(deptId, updatedName)) {
					message = "部署名を「" + updatedName + "」に変更しました。";
				} else {
					message = "変更に失敗しました。";
				}
			}
		}
		
		request.setAttribute("message", message);
		
		// 処理後、再度 doGet を呼び出して最新の一覧を表示
		doGet(request, response);
	}
}