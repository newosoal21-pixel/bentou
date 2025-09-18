package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.UserRegistLogic;
import model.EmployeeEntry;

/**
 * Servlet implementation class NewaduserServlet
 */
@WebServlet("/NewaduserServlet")
public class NewaduserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // フォームからパラメータを取得
        EmployeeEntry employeeEntry = new EmployeeEntry();
        employeeEntry.setDepartment(request.getParameter("department"));
        
        try {
            employeeEntry.setEmployeesId(Integer.parseInt(request.getParameter("userId")));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "社員IDは数値を入力してください。");
            request.getRequestDispatcher("newuser.jsp").forward(request, response);
            return;
        }
        
        employeeEntry.setPassword(request.getParameter("password"));
        String passwordCheck = request.getParameter("passwordCheck");
        employeeEntry.setUserName(request.getParameter("name"));

        // 登録ロジックを呼び出して処理を実行
        UserRegistLogic registerLogic = new UserRegistLogic();
        String errorMsg = registerLogic.validateAndRegister(employeeEntry, passwordCheck);

        if (errorMsg != null) {
            // エラーがある場合、エラーメッセージをJSPに渡してフォワード
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("newuser.jsp").forward(request, response);
        } else {
            // 成功した場合、成功画面にリダイレクト
            response.sendRedirect("userentrycomp.jsp");
        }
    }
}