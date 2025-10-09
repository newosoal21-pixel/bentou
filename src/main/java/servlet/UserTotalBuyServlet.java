package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import logic.UserTotalBuyLogic;
import model.OrderDate;
import model.TotalBuy;

@WebServlet("/UserTotalBuyServlet")
public class UserTotalBuyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 何年何月の指定があるかパラメーター取得
        String yearStr = request.getParameter("year");
        String monthStr = request.getParameter("month");
        System.out.println("yearStr=" + yearStr + " monthStr=" + monthStr);

        // 指定がなければ現在年月を使う
        LocalDate now = LocalDate.now();
        int year = (yearStr != null && !yearStr.isEmpty())? Integer.parseInt(yearStr): now.getYear();
        int month = (monthStr != null && !monthStr.isEmpty())? Integer.parseInt(monthStr): now.getMonthValue();
        System.out.println("Using year = " + year + ", month = " + month);

        // 指定された年月の合計金額を取得
        UserTotalBuyLogic logic = new UserTotalBuyLogic();
        List<TotalBuy> usageList = logic.getMonthlyTotal(year, month);

        // 年月データを取得
        OrderDAO dao = new OrderDAO();
        List<OrderDate> orderDates = new ArrayList<>();
        try {
            orderDates = dao.getOrderDates();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 年だけを取り出す Setは重複を許さない
        Set<Integer> yearSet = new LinkedHashSet<>();
        //orderDatesは「注文があった年月の組み合わせ」その中から「年だけを取り出して、重複を除いた一覧」を作る
        for (OrderDate od : orderDates) {
            yearSet.add(od.getYear());
        }

        // JSP へ渡す
        request.setAttribute("usageList", usageList);
        request.setAttribute("selectedYear", year);
        request.setAttribute("selectedMonth", month);
        request.setAttribute("orderDates", orderDates);      // 月用
        request.setAttribute("yearList", new ArrayList<>(yearSet)); // 年用

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/admin/usertotalmount.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // action が "csv" の場合のみCSV出力処理を実行
        if ("csv".equals(action)) {
            handleCsvExport(request, response);
        } else {
            // 他のPOSTリクエスト（今後追加される可能性）に対応
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action for POST request.");
        }
    }

    // ★★★ CSV出力のヘルパーメソッド ★★★
    private void handleCsvExport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. パラメーターの取得
        String yearStr = request.getParameter("year");
        String monthStr = request.getParameter("month");

        if (yearStr == null || monthStr == null) {
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("年月の情報が不足しています。");
            return;
        }

        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);

        // 2. ロジックを再実行してデータ取得
        UserTotalBuyLogic logic = new UserTotalBuyLogic();
        List<TotalBuy> usageList = logic.getMonthlyTotal(year, month);

        if (usageList == null || usageList.isEmpty()) {
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("出力対象のデータがありません。");
            return;
        }

        // --- 3. HTTPヘッダー設定 (CSVダウンロードの指示) ---
        response.setContentType("text/csv; charset=Shift_JIS"); 
        String fileName = String.format("usage_%d%02d.csv", year, month);
        response.setHeader("Content-Disposition", 
                           "attachment; filename=\"" + fileName + "\"");

        // --- 4. CSVデータの書き出し ---
        try (PrintWriter pw = response.getWriter()) {
            
            pw.println("集計年月," + year + "年" + month + "月");
            pw.println();
            
            // カラムヘッダー
            pw.println("社員ID,名前,利用金額(円)");
            
            // データ行
            for (TotalBuy totalBuy : usageList) {
                String line = String.format("%d,\"%s\",%d",
                    totalBuy.getEmployeesId(),
                    totalBuy.getUserName(), 
                    totalBuy.getTotalBuy()
                );
                pw.println(line);
            }
            
            pw.flush();
        }
    }
}

