package servlet;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.UserHisLogic;
import model.EmployeeBean;
import model.EmployeeOrder;

@WebServlet("/UserOrderListServlet")
public class UserOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserOrderListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String selectedMonth = request.getParameter("month");
		if (selectedMonth == null || selectedMonth.isEmpty()) {
			selectedMonth = YearMonth.now().toString(); // YYYY-MM形式
		}

		HttpSession session = request.getSession();
		EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
		
		 if (employee == null) {
	            // ログインしていない場合はログイン画面にリダイレクト
	            response.sendRedirect("LoginServlet"); 
	            return; // ここで処理を中断
		 }

		UserHisLogic logic = new UserHisLogic();

		List<EmployeeOrder> orderHistoryList = logic.getMonthlyOrderHistory(employee, selectedMonth);

		request.setAttribute("orderHistoryList", orderHistoryList);

		request.setAttribute("selectedMonth", selectedMonth);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userorderhistory.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
