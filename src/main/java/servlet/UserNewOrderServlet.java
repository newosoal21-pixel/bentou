package servlet;

import java.io.IOException;
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
import model.OrderBlock;

/**
 * Servlet implementation class UserNewOrderServlet
 */
@WebServlet("/UserNewOrderServlet")
public class UserNewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNewOrderServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        
     // ログインチェック
        if (employee == null) {
            response.sendRedirect("LoginServlet"); 
            return;
        }
        
        // 当日分の注文ブロックリストを取得
        UserHisLogic logic = new UserHisLogic();
        List<OrderBlock> orderList = logic.getTodayOrderBlocks(employee);

        // データをリクエストスコープに保存
        request.setAttribute("orderList", orderList);
        
		RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/usernoworder.jsp");
				dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
