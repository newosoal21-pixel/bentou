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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // フォームからパラメータを取得
        EmployeeEntry employeeEntry = new EmployeeEntry();
        int employeesId = Integer.parseInt(request.getParameter("employeesId"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String departmentName = request.getParameter("departmentName");
        
        employeeEntry.setDepartmentId(departmentId);
        try {
            employeeEntry.setEmployeesId(Integer.parseInt(request.getParameter("employeesId")));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "社員IDは数値を入力してください。");
            request.getRequestDispatcher("newuser.jsp").forward(request, response);
            return;
        }
        
        employeeEntry.setPassword(request.getParameter("password"));
        String passwordCheck = request.getParameter("passwordCheck");
        employeeEntry.setUserName(request.getParameter("userName"));

        // 登録ロジックを呼び出して処理を実行
        UserRegistLogic registerLogic = new UserRegistLogic();
        System.out.println(employeeEntry);
        String errorMsg = registerLogic.validateAndRegister(employeeEntry, passwordCheck);

        if (errorMsg != null) {
            // エラーがある場合、エラーメッセージをJSPに渡してフォワード
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp").forward(request, response);
        } else {
        	//登録成功→セッションスコープに保存しJSPに情報を渡してフォワード
        	EmployeeEntry employee = new EmployeeEntry();
        	employee.setEmployeesId(employeesId);
        	employee.setPassword(password);
        	employee.setUserName(userName);
        	employee.setDepartmentId(departmentId);
        	DepartmentRegistLogic  dl = new DepartmentRegistLogic ();
        	employee.setDepartmentName(dl.getDepartmentName(departmentId));
        	
        	HttpSession session = request.getSession();
	         session.setAttribute("employee", employee);
        	request.getRequestDispatcher("/WEB-INF/jsp/preentryuser.jsp").forward(request, response);

        }
    }
}