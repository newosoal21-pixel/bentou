package logic;

import java.util.List;

import dao.ProductDAO;
import model.Product;

public class ProductLogic {
	ProductDAO dao = new ProductDAO();

	public List<Product> findByDetail(Product p){	
		return dao.findByDetail(p);
	}
}
