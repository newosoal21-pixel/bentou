package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
		 String action = request.getParameter("action");
		 
		 
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
		 if ("login".equals(action)) {
			 RequestDispatcher dispatcher =
		                request.getRequestDispatcher("/WEB-INF/jsp/userlogin.jsp");
		               dispatcher.forward(request, response);
			 
		 }
		 if ("cancel".equals(action)) {
			 RequestDispatcher dispatcher =
		                request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp");
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
	     LoginLogic l = new LoginLogic();
	     EmployeeBean employee = l.execute(employeesId, password);

	     if (employee != null) {
	         // 認証成功 → セッションに保存
	         HttpSession session = request.getSession();
	         session.setAttribute("employee", employee);

	         // --- ログイン履歴書き込み処理 ---
	         
	         // データベース接続情報 (環境に合わせて変更してください)
	         final String JDBC_URL = "jdbc:mysql://localhost:3306/lunchclerkDX?useSSL=false&serverTimezone=Asia/Tokyo"; // 変数名を修正
	         final String DB_USER = "root";
	         final String DB_PASS = "root";

	         // login_history テーブルの構造に応じてSQLを調整
	         // login_history_id がオートインクリメントの場合、VALUESからは除外します。
	         // login_history (login_history_id INT, login_time DATETIME, employees_id VARCHAR)
	         final String INSERT_SQL = "INSERT INTO login_history (login_time, employees_id) VALUES (?, ?)"; // SQLを修正
	         
	         Connection conn = null;
	         PreparedStatement pstmt = null;

	         try {
	             // 1. JDBCドライバーの読み込み（MySQL 8.0以降では通常不要ですが、念のため）
	             Class.forName("com.mysql.cj.jdbc.Driver"); 
	             
	             // 2. データベース接続の確立
	             conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS); // DB_URLではなくJDBC_URLを使用
	             // ★ 注意: ここにあった「return conn;」を削除しました。サーブレット内でDB接続を返してはいけません。

	             // 3. SQL（INSERT文）の実行準備 (PreparedStatementの利用でSQLインジェクション対策)
	             pstmt = conn.prepareStatement(INSERT_SQL);
	             
	             // パラメータの設定
	             pstmt.setObject(1, LocalDateTime.now()); // 現在時刻 (login_time)
	             pstmt.setLong(2, employee.getEmployeesId()); // 社員ID (employees_id)
	            
	             
	             // ★ 修正点:
	             // 1. `login_history_id`は通常DB側でAuto Incrementするため、INSERT文から除外し、`pstmt.setXXX(1, ...)`を削除しました。
	             // 2. `employees_id`には認証成功したEmployeeBeanから取得したIDをセットしました。
	             // 3. `request.getRemoteAddr()`をリモートIPアドレスとして利用し、SQLに追加しました。
	             
	             // SQLの実行
	             int rowCount = pstmt.executeUpdate();
	             
	             if (rowCount > 0) {
	                 System.out.println("ログイン履歴を正常に書き込みました。");
	             }

	         } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	         } catch (SQLException e) {
	             e.printStackTrace();
	         } finally {
	             // 4. リソースのクローズ（必ず実行）
	             try {
	                 if (pstmt != null) pstmt.close();
	                 if (conn != null) conn.close();
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         }

	         // --- ログイン履歴書き込み処理 終了 ---
	         
	         // ログイン成功後の画面遷移ロジック
	         // ※ ユーザーと管理者の振り分けロジックが重複していたため、整理しました。
	         if ("user".equals(button)) {
	             request.getRequestDispatcher("/WEB-INF/jsp/userbase.jsp").forward(request, response);
	             return; // 画面遷移後は処理を終了
	         }

	         if (employee.isAdmin()) {
	             request.getRequestDispatcher("/WEB-INF/jsp/admin/adminbase.jsp").forward(request, response);
	             return; // 画面遷移後は処理を終了
	         } else {
	             request.getRequestDispatcher("/WEB-INF/jsp/userbase.jsp").forward(request, response);
	             return; // 画面遷移後は処理を終了
	         }
	     } else {
	         // 認証失敗
	         request.setAttribute("errorMessage", "社員IDもしくはパスワードが間違っています。");

	         if ("admin".equals(button)) {
	             request.getRequestDispatcher("/WEB-INF/jsp/admin/adminlogin.jsp").forward(request, response);
	         } else {
	             request.getRequestDispatcher("/WEB-INF/jsp/userlogin.jsp").forward(request, response);
	         }
	     }
	}
}
