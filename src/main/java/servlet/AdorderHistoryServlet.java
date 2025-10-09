package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.AdoderHisLogic;
import model.AdOrderHistory;

/**
 * Servlet implementation class AdorderHistoryServlet
 */
@WebServlet("/AdorderHistoryServlet")
public class AdorderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdorderHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// リクエストの文字コード設定
	    request.setCharacterEncoding("UTF-8");
		
		String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    String day = request.getParameter("day");
		
		AdoderHisLogic logic = new AdoderHisLogic();
		
		List<AdOrderHistory> totalOrderDateList = null;
		
		try {
	        if ((year != null && !year.isEmpty()) &&
	            (month != null && !month.isEmpty()) &&
	            (day != null && !day.isEmpty())) {
	            String selectedDate = String.format("%04d-%02d-%02d",
	                    Integer.parseInt(year),
	                    Integer.parseInt(month),
	                    Integer.parseInt(day));
	            totalOrderDateList = logic.executeByDate(selectedDate);
	        } else {
	            totalOrderDateList = logic.execute();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		request.setAttribute("totalOrderDateList", totalOrderDateList);
		
		// 検索条件をJSPに渡す
		request.setAttribute("paramYear", year);
		request.setAttribute("paramMonth", month);
		request.setAttribute("paramDay", day);
		
		RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/admin/orderhistory.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// リクエストの文字コード設定
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if ("csv".equals(action)) {
			// CSV出力処理
			try {
				handleCsvExport(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "CSV出力中にデータベースエラーが発生しました。");
			}
		} else {
			// CSV以外のPOSTリクエストはGETに転送
			doGet(request, response);
		}
	}
	
	private void handleCsvExport(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException, SQLException {
	    
	    String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    String day = request.getParameter("day");
	    
	    AdoderHisLogic logic = new AdoderHisLogic();
	    List<AdOrderHistory> totalOrderDateList = null;
	    
	    // 検索条件に基づいて再度データ取得
	    if ((year != null && !year.isEmpty()) &&
	        (month != null && !month.isEmpty()) &&
	        (day != null && !day.isEmpty())) {
	        String selectedDate = String.format("%04d-%02d-%02d",
	                Integer.parseInt(year),
	                Integer.parseInt(month),
	                Integer.parseInt(day));
	        totalOrderDateList = logic.executeByDate(selectedDate);
	    } else {
	        totalOrderDateList = logic.execute();
	    }
	    
	    if (totalOrderDateList == null || totalOrderDateList.isEmpty()) {
	        response.setContentType("text/plain; charset=UTF-8");
	        response.getWriter().write("出力対象のデータがありません。");
	        return;
	    }
	    
	    // --- HTTPヘッダー設定 (CSVダウンロードの指示) ---
	    response.setContentType("text/csv; charset=Shift_JIS"); 
	    String fileName = "order_history.csv";
	    response.setHeader("Content-Disposition", 
	                       "attachment; filename=\"" + fileName + "\"");

	    // --- CSVデータの書き出し ---
	    try (PrintWriter pw = response.getWriter()) {
	        
	        // カラムヘッダー
	        pw.println("日付,商品名,個数,金額(円)");
	        
	        // データ行
	        for (AdOrderHistory order : totalOrderDateList) {
	            String line = String.format("%s,\"%s\",%d,%d",
	                order.getOrderDate().toString(),
	                order.getItemName(), 
	                order.getTotalQuantity(),
	                order.getTotalPrice()
	            );
	            pw.println(line);
	        }
	        
	        pw.flush();
	    }
	}
}