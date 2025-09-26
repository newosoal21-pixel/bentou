package servlet;

import java.io.IOException;
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
		
		String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    String day = request.getParameter("day");
		
		AdoderHisLogic logic = new AdoderHisLogic();
		
		List<AdOrderHistory> totalOrderDateList = null;
		
		
		try {
	        if ((year != null && !year.isEmpty()) &&
	            (month != null && !month.isEmpty()) &&
	            (day != null && !day.isEmpty())) {

	            // 選択された日付を YYYY-MM-DD 形式に整形
	            String selectedDate = String.format("%04d-%02d-%02d",
	                    Integer.parseInt(year),
	                    Integer.parseInt(month),
	                    Integer.parseInt(day));

	            totalOrderDateList = logic.executeByDate(selectedDate);
	        } else {
	            // 日付未選択なら全件取得
	            totalOrderDateList = logic.execute();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		request.setAttribute("totalOrderDateList", totalOrderDateList);
		
		
		
		RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/admin/orderhistory.jsp");
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
