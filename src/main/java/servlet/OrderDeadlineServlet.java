package servlet;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.DeadlineLogic;
import model.OrderDeadline;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/OrderDeadlineServlet")
public class OrderDeadlineServlet extends HttpServlet {
    private DeadlineLogic logic = new DeadlineLogic();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDeadline deadline = logic.getDeadline();

        if (deadline != null && deadline.getDeadlineTime() != null) {
            String timeStr = deadline.getDeadlineTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            request.setAttribute("deadlineTime", timeStr);  
        } else {
            request.setAttribute("deadlineTime", "未設定");
        }

        request.getRequestDispatcher("WEB-INF/jsp/admin/orderdeadline.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String newTime = request.getParameter("newTime");

        boolean updated = logic.updateDeadline(newTime);

        if (updated) {
            request.setAttribute("message", "締め切り時間を「" + newTime + "」に更新しました。");
        } else {
            request.setAttribute("message", "更新できませんでした。");
        }

        // 最新の値を取得して表示
        doGet(request, response);
    }
}