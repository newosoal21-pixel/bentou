package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AdminMainServlet
 */
@WebServlet("/AdminMainServlet")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ここに認証チェックや、adcontrol.jspで必要なデータを取得するなどの処理を追加できます
        // 例: セッションに管理者情報があるか確認
        // if (request.getSession().getAttribute("admin") == null) {
        //     response.sendRedirect("login.jsp"); // 認証されていなければログイン画面へ
        //     return;
        // }

        // adcontrol.jspにフォワード
        String forwardPath = "/WEB-INF/jsp/admin/adcontrol.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
        dispatcher.forward(request, response);
    }
}
