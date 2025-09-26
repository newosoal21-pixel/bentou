package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.DeadlineLogic;

/**
 * Servlet implementation class DeadlineServlet
 */
@WebServlet("/deadline")
public class DeadlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//確認用
		//doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 締め切りの時刻を取得しJSON形式で返却します
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String hour = ""; //締め切り時
		String minute = ""; //締め切り分
		try {
			DeadlineLogic dl = new DeadlineLogic();
			String deadline = dl.getDeadlineStr(); //締め切りを取得(hh:mm形式)
			System.out.println(deadline);//11:00:00
			if (deadline != null && deadline.length() == 8) {
				hour = deadline.substring(0, 2);
				minute = deadline.substring(3, 5);
				System.out.println(hour);
				System.out.println(minute);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//DBでエラーが発生したらエラーコードを返却する
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		// JSONレスポンスを返す
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (!hour.isEmpty() && !minute.isEmpty()) {
			out.print("{\"status\":\"ok\",");
			out.print("\"hour\":\"" + hour + "\",");
			out.print("\"minute\":\"" + minute + "\"}");
		} else {
			out.print("{\"status\":\"ng\"}");
		}
		out.flush();
	}

}
