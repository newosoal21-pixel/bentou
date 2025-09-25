package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.UserHisLogic;
import logic.UserHisLogic.MonthlySummary;
import model.EmployeeBean;

/**
 * Servlet implementation class UserMypageServlet
 */
@WebServlet("/UserMypageServlet")
public class UserMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMypageServlet() {
        super();
       
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();  
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");

        // 合計金額とカロリー取得
        UserHisLogic logic = new UserHisLogic();
        MonthlySummary summary = logic.getMonthlySummary(employee);

        request.setAttribute("totalAmount", summary.getTotalPrice());
        request.setAttribute("totalCalories", summary.getTotalCal());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usermypage.jsp");
        dispatcher.forward(request, response);
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
