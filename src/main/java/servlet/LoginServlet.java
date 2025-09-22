package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.LoginLogic;
import model.EmployeeBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//入力値の取得
		 String next = request.getParameter("next");
		 String button = request.getParameter("button");
		 
		 if("login".equals(next)) {
				RequestDispatcher dispatcher =
		                request.getRequestDispatcher("/WEB-INF/jsp/userlogin.jsp");
		               dispatcher.forward(request, response);
			 
		 }else if("new".equals(next)) {
				RequestDispatcher dispatcher =
		                request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp");
		               dispatcher.forward(request, response);
			 
		 }else if("adminlogin".equals(next)) {
				RequestDispatcher dispatcher =
		                request.getRequestDispatcher("/WEB-INF/jsp/admin/admintop.jsp");
		               dispatcher.forward(request, response);
		 }
		 if(button != null) {
			 RequestDispatcher dispatcher =
		                request.getRequestDispatcher("/WEB-INF/jsp/admin/adminlogin.jsp");
		               dispatcher.forward(request, response);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		request.setCharacterEncoding("UTF-8");
				
		 String idStr = request.getParameter("employeesId");
		 String pass = request.getParameter("password");

		    // ログで確認
		    System.out.println("employeesId: " + idStr);
		    System.out.println("password: " + (pass));
		//入力値の取得
		 int employeesId = Integer.parseInt(request.getParameter("employeesId"));
	     String password = request.getParameter("password");
	     String button = request.getParameter("button");
	     
	  // ログインチェック
	     LoginLogic l=new LoginLogic();
	     EmployeeBean employee = l.execute(employeesId, password);

	     if (employee != null) {
	         // 認証成功 → セッションに保存
	         HttpSession session = request.getSession();
	         session.setAttribute("employee", employee);
	         
	         if("user".equals( button)) {
	        	 request.getRequestDispatcher("/WEB-INF/jsp/userbase.jsp").forward(request, response);
	        	 return;
	         }
	         
	         if (employee.isAdmin()) {
	             request.getRequestDispatcher("/WEB-INF/jsp/admin/adminbase.jsp").forward(request, response);
	        	 return;
	         } else {
	             request.getRequestDispatcher("/WEB-INF/jsp/userbase.jsp").forward(request, response);
	        	 return;
	         }

	        	 
	        } else {
	            // 認証失敗
	            request.setAttribute("errorMessage", "社員IDもしくはパスワードが間違っています。");

	            // ログイン画面に戻る
	            request.getRequestDispatcher("/WEB-INF/jsp/userlogin.jsp").forward(request, response);
	        }
	     
	    	 
	     }

}
