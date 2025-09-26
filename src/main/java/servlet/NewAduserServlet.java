package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.DepartmentRegistLogic;
import logic.UserRegistLogic;
import model.EmployeeEntry;

/**
 * Servlet implementation class NewaduserServlet
 */
@WebServlet("/NewAduserServlet")
public class NewAduserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		UserRegistLogic registerLogic = new UserRegistLogic();
		// フォームからパラメータを取得
		EmployeeEntry employeeEntry = new EmployeeEntry();
		int employeesId = Integer.parseInt(request.getParameter("employeesId"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		String departmentName = request.getParameter("departmentName");

		employeeEntry.setDepartmentId(departmentId);

		String button = request.getParameter("button");
		System.out.println(button);

		try {
			employeeEntry.setEmployeesId(Integer.parseInt(request.getParameter("employeesId")));
		} catch (NumberFormatException e) {
			request.setAttribute("errorMsg", "社員IDは数値を入れてください");
			request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp").forward(request, response);
			return;
		}

		employeeEntry.setPassword(request.getParameter("password"));
		String passwordCheck = request.getParameter("passwordCheck");
		employeeEntry.setUserName(request.getParameter("userName"));

		// 登録ロジックを呼び出して処理を実行
		System.out.println(employeeEntry);
		if ("OK".equals(button)) {
			String msg = registerLogic.regist(employeeEntry);
			if (msg != null) {
				request.setAttribute("errorMsg", msg);
				request.getRequestDispatcher("/WEB-INF/jsp/preentryuser.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/userentrycomp.jsp").forward(request, response);
				return;
			}
		} else {
			//入力チェック
			//String errorMsg = registerLogic.validateAndRegister(employeeEntry, passwordCheck);
			String errorMsg = registerLogic.validate(employeeEntry, passwordCheck);
			System.out.println(1);
			if (errorMsg != null) {
				// エラーがある場合、エラーメッセージをJSPに渡してフォワード
				request.setAttribute("errorMsg", errorMsg);
			} else {
				//登録成功→セッションスコープに保存しJSPに情報を渡してフォワード
				EmployeeEntry employee = new EmployeeEntry();
				employee.setEmployeesId(employeesId);
				employee.setPassword(password);
				employee.setUserName(userName);
				employee.setDepartmentId(departmentId);
				DepartmentRegistLogic dl = new DepartmentRegistLogic();
				employee.setDepartmentName(dl.getDepartmentName(departmentId));

				HttpSession session = request.getSession();
				session.setAttribute("employee", employee);
				request.getRequestDispatcher("/WEB-INF/jsp/preentryuser.jsp").forward(request, response);

			}
		}
	}
}