package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.UserEditLogic;
import model.EmployeeBean;

@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/useredit.jsp");
		dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字化け防止
        request.setCharacterEncoding("UTF-8");

        // JSPから値を取得
        String departmentIdStr = request.getParameter("departmentId"); // name属性変更
        String name = request.getParameter("name");
        
        System.out.println(departmentIdStr);
        System.out.println(name);
        
        // int型に変換
        int departmentId = Integer.parseInt(departmentIdStr); 
        
     // セッションからEmployeeBeanオブジェクトを取得し、そこからemployeesId（int型）を取得する
        HttpSession session = request.getSession();
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee"); // LoginServletでemployeeというキーで保存されている 
        
        // セッションにIDがない場合はエラー処理（未ログイン、セッション切れ）
        if (employee == null) {
            response.sendRedirect("LoginServlet"); // ログイン画面へリダイレクトなど
            return;
        }
        
     // EmployeeBeanからIDを取得
        int employeesId = employee.getEmployeesId();

        // ロジック呼び出し部分を修正 (Integer型からint型に変わったため、intValue()は不要)
        UserEditLogic logic = new UserEditLogic();
        boolean result = logic.execute(name, departmentId, employeesId);

       
        // 処理結果で遷移先を分ける
        if (result) {
        	
            // ★★★ 修正点: DAO（Logic）経由で部署名を取得 ★★★
            // 部署IDから部署名をDBから取得
            String departmentName = logic.getDepartmentName(departmentId);
            // ★★★ 修正終わり ★★★
        	
            // ★★★ 修正点: セッションに反映する処理 ★★★
            // 1. EmployeeBeanのプロパティを更新
            employee.setUserName(name); 
            employee.setDepartmentId(departmentId);
            employee.setDepartmentName(departmentName); // DBから取得した最新の部署名をセット
            
            System.out.println(departmentName);
            
            // 2. 更新後のEmployeeBeanをセッションに上書き保存
            session.setAttribute("employee", employee); 
            // ★★★ 修正終わり ★★★
        	
            request.setAttribute("message", "更新が完了しました！");
        } else {
            request.setAttribute("message", "更新に失敗しました。");
        }

        // 結果ページへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/usereditresult.jsp").forward(request, response);
    }
}
