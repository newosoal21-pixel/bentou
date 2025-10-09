package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.AdPreorderLogic;
import model.AdminOrder;

/**
 * Servlet implementation class AdminOrderServlet
 */
@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdPreorderLogic adPreorderLogic = new AdPreorderLogic();
	    List<AdminOrder> orderList = adPreorderLogic.execute();

	    int totalAmount = 0;
	    for (AdminOrder order : orderList) {
	        totalAmount += order.getTotalPrice();
	    }
	    
	    int totalQuantity = 0;
	    for (AdminOrder order : orderList) {
	        totalQuantity += order.getTotalQuantity();
	    }


	    // 今日の日付を取得
	    Date orderDate = new Date(System.currentTimeMillis());

	    session.setAttribute("orderList", orderList);
	    session.setAttribute("totalAmount", totalAmount);
	    session.setAttribute("orderDate", orderDate); // ←ここを追加
	    session.setAttribute("totalQuantity", totalQuantity);
	    
	    //boolean orderCompleted 
	    boolean orderCompleted = orderList != null && orderList.stream()
	    	    .allMatch(o -> o.getOrderFlag() == 1);
	    System.out.println("orderCompleted" + orderCompleted);
	    	request.setAttribute("orderCompleted", orderCompleted);
	    	
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/adpreorder.jsp");
		    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String action = request.getParameter("action");
	    HttpSession session = request.getSession(); // セッションを取得

	    if ("csv".equals(action)) {
	        // ★CSV出力処理
	        try {
	            handleCsvExport(request, response, session);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "CSV出力中にエラーが発生しました。");
	        }
	        return; // CSV出力後はフォワードせずに終了
	    }

	    // --- 既存の発注処理（DB更新、画面表示のためのデータ取得）---
	    AdPreorderLogic logic = new AdPreorderLogic();

	    // 既存の発注ロジック
	    int updatedCount = logic.markTodayOrdersAsOrdered();
	    List<AdminOrder> orderList = logic.execute();
	    Map<String, List<AdminOrder>> map = logic.getDepartmentOrders();
	    boolean orderCompleted = orderList.isEmpty();

	    // 合計金額計算
	    Map<String, Integer> totalPriceMap = new HashMap<>();
	    int totalAmount = 0; // 総合合計金額も計算し直す
	    int totalQuantity = 0; // 総合合計個数も計算し直す

	    for (Map.Entry<String, List<AdminOrder>> entry : map.entrySet()) {
	        int sum = 0;
	        for (AdminOrder order : entry.getValue()) {
	            sum += order.getTotalPrice();
	            totalAmount += order.getTotalPrice();
	            totalQuantity += order.getTotalQuantity();
	        }
	        totalPriceMap.put(entry.getKey(), sum);
	    }
	    
	 // ★修正: mapから総合集計リスト (orderList) を作成し、
	 // ★これを最終的に画面に渡すリストとする
	 List<AdminOrder> orderListForDisplay = logic.getOrdersFromMap(map); 
	    
	    Date orderDate = new Date(System.currentTimeMillis()); // 日付を再取得

	    // ★★★ 画面表示データとCSV出力を兼ねて、セッションにも保存する ★★★
	    session.setAttribute("orderList", orderListForDisplay); // ★修正
	    session.setAttribute("totalAmount", totalAmount);
	    session.setAttribute("orderDate", orderDate);
	    session.setAttribute("totalQuantity", totalQuantity);
	    session.setAttribute("map", map); // 部署別リスト
	    session.setAttribute("totalPriceMap", totalPriceMap); // 部署別合計金額

	    
	    // リクエスト属性はフォワード先でのみ使用
	    request.setAttribute("orderList", orderListForDisplay); // ★修正
	    request.setAttribute("totalAmount", totalAmount);
	    request.setAttribute("orderDate", orderDate);
	    request.setAttribute("totalQuantity", totalQuantity);
	    request.setAttribute("orderCompleted", orderCompleted);
	    request.setAttribute("updatedCount", updatedCount);
	    request.setAttribute("map", map);
	    request.setAttribute("totalPriceMap", totalPriceMap);

	    request.getRequestDispatcher("/WEB-INF/jsp/admin/adordercomp.jsp")
	           .forward(request, response);
	}

	// ★★★ CSV出力のヘルパーメソッドを追加 ★★★
	private void handleCsvExport(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	        throws IOException {
	    
	    // セッションから表示データを取得
	    // ★★★ 型の安全性を確保するため、キャストエラー時に備えてnullで初期化 ★★★
	    List<AdminOrder> orderList = null;
	    Map<String, List<AdminOrder>> map = null;
	    String orderDateStr = null;
	    Integer totalAmount = null;
	    Integer totalQuantity = null;
	    Map<String, Integer> totalPriceMap = null;
	    
	    try {
	        orderList = (List<AdminOrder>) session.getAttribute("orderList");
	        map = (Map<String, List<AdminOrder>>) session.getAttribute("map");
	        orderDateStr = session.getAttribute("orderDate").toString();
	        totalAmount = (Integer) session.getAttribute("totalAmount");
	        totalQuantity = (Integer) session.getAttribute("totalQuantity");
	        totalPriceMap = (Map<String, Integer>) session.getAttribute("totalPriceMap");
	    } catch (Exception e) {
	        System.err.println("CSV出力時: セッションからのデータ取得でエラーが発生しました。");
	        e.printStackTrace();
	    }
	    
	    // ★★★ デバッグ出力の追加 (CSV出力リクエスト時) ★★★
	    System.out.println("--- CSV出力時のセッションデータ確認 ---");
	    System.out.println("orderList size (CSV): " + (orderList != null ? orderList.size() : "null"));
	    System.out.println("map size (CSV): " + (map != null ? map.size() : "null"));
	    System.out.println("------------------------------------");

	    // ★★★ データチェックロジックの修正: mapが有効で空でなければOKとする ★★★
	    // 部署別集計 (map) にデータがあれば、それは発注データがあると判断できる
	    if (map == null || map.isEmpty()) { 
	        response.setContentType("text/plain; charset=UTF-8");
	        response.getWriter().write("出力対象の注文データがありません。");
	        return;
	    }

	    // --- HTTPヘッダー設定 ---
	    response.setContentType("text/csv; charset=Shift_JIS"); 
	    String fileName = "order_completion_" + orderDateStr.replace("-", "") + ".csv";
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

	    // --- CSVデータの書き出し ---
	    try (PrintWriter pw = response.getWriter()) {
	        
	        // 1. 総合集計
	        pw.println("--- 総合集計 ---");
	        pw.println("日付," + orderDateStr);
	        pw.println("総合合計金額," + totalAmount);
	        pw.println("総合合計個数," + totalQuantity);
	        pw.println("名前,個数,金額(円)");
	        for (AdminOrder order : orderList) {
	            pw.printf("\"%s\",%d,%d%n", order.getItemName(), order.getTotalQuantity(), order.getTotalPrice());
	        }
	        pw.println(); 
	        
	        // 2. 部署別集計
	        pw.println("--- 部署別集計 ---");
	        
	        for (Map.Entry<String, List<AdminOrder>> entry : map.entrySet()) {
	            String departmentName = entry.getKey();
	            List<AdminOrder> deptOrders = entry.getValue();
	            
	            pw.println("部署名," + departmentName);
	            pw.println("日付," + orderDateStr);
	            pw.println("部署別合計金額," + totalPriceMap.get(departmentName));
	            pw.println("名前,個数,金額(円)");

	            for (AdminOrder order : deptOrders) {
	                 pw.printf("\"%s\",%d,%d%n", order.getItemName(), order.getTotalQuantity(), order.getTotalPrice());
	            }
	            pw.println(); // 部署ごとに空行
	        }
	        
	        pw.flush();
	    }
	}
}