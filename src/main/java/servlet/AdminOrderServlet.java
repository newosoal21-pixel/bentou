package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.AdPreorderLogic;
import model.AdminOrder;

/**
 * Servlet implementation class AdminOrderServlet
 */
@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdPreorderLogic adPreorderLogic = new AdPreorderLogic();
	    List<AdminOrder> orderList = adPreorderLogic.execute();

	    int totalAmount = 0;
	    for (AdminOrder order : orderList) {
	        totalAmount += order.getTotalPrice();
	    }
	    
	    int totalQuantity = 0;
	    for (AdminOrder order : orderList) {
	        totalQuantity += order.getTotalQuantity();
	    }


	    // 今日の日付を取得
	    Date orderDate = new Date(System.currentTimeMillis());

	    request.setAttribute("orderList", orderList);
	    request.setAttribute("totalAmount", totalAmount);
	    request.setAttribute("orderDate", orderDate); // ←ここを追加
	    request.setAttribute("totalQuantity", totalQuantity);
	    
	    //boolean orderCompleted 
	    boolean orderCompleted = orderList != null && orderList.stream()
	    	    .allMatch(o -> o.getOrderFlag() == 1);
	    System.out.println("orderCompleted" + orderCompleted);
	    	request.setAttribute("orderCompleted", orderCompleted);
	    	
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/adpreorder.jsp");
		    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB更新処理
		AdPreorderLogic logic = new AdPreorderLogic();

	    // 発注状態をDBで更新（orders.order_flag = 1）
	    int updatedCount = logic.markTodayOrdersAsOrdered();

	    // 更新後の最新リストを取得して表示
	    List<AdminOrder> orderList = logic.execute();

	    boolean orderCompleted = orderList != null && orderList.stream()
	        .allMatch(o -> o.getOrderFlag() == 1);

	    request.setAttribute("orderList", orderList);
	    request.setAttribute("orderCompleted", orderCompleted);
	    request.setAttribute("updatedCount", updatedCount);

	    request.getRequestDispatcher("/WEB-INF/jsp/admin/adordercomp.jsp")
	           .forward(request, response);
	}
}