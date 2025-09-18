package logic;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import model.Product;

public class ProductviewLogic {
	public List<Product> execute(){
		ProductDAO dao = new ProductDAO();
		List<Product> productList = dao.findByDetail();
		
		// nullチェックを追加
				if (productList == null) {
					productList = new ArrayList<>(); // 空のリストで初期化
				}

		return productList;
	}
}
