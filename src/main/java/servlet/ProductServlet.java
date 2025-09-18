package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.ProductLogic;
import model.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品の検索
		//本来ならフォームに入力した検索条件を取得してBeanを作成する
		Product p1 = new Product(1,null,100);
		Product p2 = new Product(2,null,0);
		Product p3 = new Product(0,"商品",0);
		
		ProductLogic pl = new ProductLogic();
		
		List<Product> list1 =  pl.findByDetail(p1);
		List<Product> list2 =  pl.findByDetail(p2);
		List<Product> list3 =  pl.findByDetail(p3);
		
		//確認
		System.out.println("p1");
		show(list1);
		System.out.println("p2");
		show(list2);
		System.out.println("p3");
		show(list3);
		
		//本来ならJSPに転送する処理を記述
		
	}

	private void show(List<Product> list) {
		for(Product p:list) {
			System.out.println(p.toString());
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
