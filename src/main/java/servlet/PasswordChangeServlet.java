package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.ChangePasswordLogic;
import model.EmployeeBean;

@WebServlet("/PasswordChangeServlet")
public class PasswordChangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PasswordChangeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/changepass.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // セッションから社員情報を取得
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        if (employee == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        // ValueOfは値を文字列に変換するメソッド
        String employeeId = String.valueOf(employee.getEmployeesId());

        // フォームから送られたパスワードを取得
        String nowPassword = request.getParameter("nowPassword");
        String changePassword = request.getParameter("changePassword");
        String comparisonPassword = request.getParameter("comparisonPassword");

        ChangePasswordLogic logic = new ChangePasswordLogic();
        Map<String, String> errors = logic.validateAndChangePassword(
                employeeId, nowPassword, changePassword, comparisonPassword);

        if (!errors.isEmpty()) {
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/changepass.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/changepasscomp.jsp");
        dispatcher.forward(request, response);
    }
}
