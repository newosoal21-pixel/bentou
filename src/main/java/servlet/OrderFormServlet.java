package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.OrderDAO;
import logic.ProductviewLogic;
import model.EmployeeBean;
import model.EmployeeOrder;
import model.Product;

@WebServlet("/OrderFormServlet")
public class OrderFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderFormServlet() {
        super();  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		
		ProductviewLogic productviewLogic = new ProductviewLogic();
		List<Product> productList = productviewLogic.execute();
		request.setAttribute("productList", productList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/orderform.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");

		if ("submit".equals(action)) {
		    List<EmployeeOrder> orderList = (List<EmployeeOrder>) session.getAttribute("orderList");

		    try {
		        OrderDAO dao = new OrderDAO();
		        EmployeeBean employee = (EmployeeBean) session.getAttribute("loginEmployee");
		        
		        /* テスト用 */
		        if (employee == null) {
		            employee = new EmployeeBean();
		            employee.setEmployeesId(999); // 仮の従業員ID
		     
		        }
		        dao.saveOrders(orderList, employee); // ← セッションから取得した employee を渡す
		    } catch (SQLException e) {
		        e.printStackTrace();

		    }

		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ordercomp.jsp");
		    dispatcher.forward(request, response);
		    return;
		}


		
		
		ProductviewLogic logic = new ProductviewLogic();
	    List<Product> productList = logic.execute();

	    List<EmployeeOrder> orderList = new ArrayList<>();
	    for (Product product : productList) {
	        String quantityStr = request.getParameter(String.valueOf(product.getId()));
	        if (quantityStr != null && !quantityStr.isEmpty()) {
	            int quantity = Integer.parseInt(quantityStr);
	            if (quantity > 0) {
	                int totalPrice = quantity * product.getPrice();
	                EmployeeOrder order = new EmployeeOrder(
	                    LocalDateTime.now(),
	                    product.getName(),
	                    quantity,
	                    totalPrice
	                );
	                orderList.add(order);
	            }
	        }
	    }
	 

	    
	    int totalAmount = 0;
	    for (EmployeeOrder order : orderList) {
	        totalAmount += order.getTotalPrice();
	    }
	    session.setAttribute("orderList", orderList);
	    session.setAttribute("totalAmount", totalAmount);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/preorder.jsp");
	    dispatcher.forward(request, response);

	}

}
