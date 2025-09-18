package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.EmployeeBean;

/**
 * Servlet implementation class UserMainServlet
 */
@WebServlet("/UserMainServlet")
public class UserMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserMainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		EmployeeBean loginEmployee = (EmployeeBean)session.getAttribute("loginEmployee");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userbase.jsp");
		dispatcher.forward(request, response);
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
