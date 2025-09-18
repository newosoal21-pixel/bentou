package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import model.EmployeesList;

@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchIdStr = request.getParameter("searchId");
        List<EmployeesList> employeeList;
        EmployeesDAO dao = new EmployeesDAO();
        
        System.out.println("searchIdStr=" + searchIdStr);

        if (searchIdStr != null && !searchIdStr.trim().isEmpty()) {
            int searchId = Integer.parseInt(searchIdStr.trim());  
            EmployeesList emp = dao.findById(searchId);
            employeeList = (emp != null) ? List.of(emp) : List.of();
        } else {
            employeeList = dao.findAll();
        }

        request.setAttribute("employeeList", employeeList);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/userdelete.jsp");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("userdelete.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        EmployeesDAO dao = new EmployeesDAO();

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr); 
            boolean success = dao.deleteById(id);
            request.setAttribute("deleteResult", success ? "削除しました" : "削除できませんでした");
        }

        List<EmployeesList> employeeList = dao.findAll();
        request.setAttribute("employeeList", employeeList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/userdelete.jsp");
        dispatcher.forward(request, response);
    }
}

