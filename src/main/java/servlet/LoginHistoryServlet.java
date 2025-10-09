package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.LogHisLogic;
import model.LoginHistory;

/**
 * Servlet implementation class LoginHistoryServlet
 */
@WebServlet("/LoginHistoryServlet")
public class LoginHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LogHisLogic logic = new LogHisLogic();
		
		// データ取得
		List<LoginHistory> loginHisList = logic.execute();
		
		request.setAttribute("loginHisList", loginHisList);
		
		
		RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/admin/adloginhistory.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("csv".equals(action)) {
			// CSV出力処理
			handleCsvExport(request, response);
		} else {
			// CSV以外のPOSTリクエストはGETに転送
			doGet(request, response);
		}
	}
	
	// ★★★ CSV出力のヘルパーメソッド ★★★
	private void handleCsvExport(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    LogHisLogic logic = new LogHisLogic();
	    // データ取得を再実行
	    List<LoginHistory> loginHisList = logic.execute();
	    
	    if (loginHisList == null || loginHisList.isEmpty()) {
	        response.setContentType("text/plain; charset=UTF-8");
	        response.getWriter().write("出力対象のデータがありません。");
	        return;
	    }
	    
	    // --- HTTPヘッダー設定 (CSVダウンロードの指示) ---
	    // Shift_JISはExcelでの文字化け防止のため推奨
	    response.setContentType("text/csv; charset=Shift_JIS"); 
	    String fileName = "login_history.csv";
	    response.setHeader("Content-Disposition", 
	                       "attachment; filename=\"" + fileName + "\"");

	    // --- CSVデータの書き出し ---
	    try (PrintWriter pw = response.getWriter()) {
	        
	        // カラムヘッダー
	        pw.println("社員ID,名前,ログイン日時");
	        
	        // データ行
	        for (LoginHistory history : loginHisList) {
	            // 名前はカンマを含む可能性があるため、ダブルクォーテーションで囲む
	            String line = String.format("%d,\"%s\",%s",
	                history.getEmployeesId(),
	                history.getUserName(), 
	                history.getLoginTimeStr()
	            );
	            pw.println(line);
	        }
	        
	        pw.flush();
	    }
	}
}