package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
	
	//検索条件を元に検索
	public List<Product> findByDetail(Product p) {
	    List<Product> productList = new ArrayList<>();

	    StringBuilder sql = new StringBuilder("SELECT * FROM PRODUCTS ");
	    
	    //WHERE句
	    StringBuilder where = new StringBuilder();

	    if (p.getId() != 0) {
	        where.append("ID = ? ");
	    }
	    if (p.getName() != null && !p.getName().isEmpty()) {
	        if (where.length() > 0) where.append("AND ");
	        where.append("NAME LIKE ? ");
	    }
	    if (p.getPrice() != 0) {
	        if (where.length() > 0) where.append("AND ");
	        where.append("PRICE = ? ");
	    }

	    if (where.length() > 0) {
	        sql.append(" WHERE ").append(where);
	    }
	    //確認用
	    System.out.println(sql.toString());
	    
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement pStmt = conn.prepareStatement(sql.toString())) {

	        int num = 1;
	        if (p.getId() != 0) {
	            pStmt.setInt(num++, p.getId());
	        }
	        if (p.getName() != null && !p.getName().isEmpty()) {
	            pStmt.setString(num++, "%" + p.getName() + "%");
	        }
	        if (p.getPrice() != 0) {
	            pStmt.setInt(num++, p.getPrice());
	        }

	        try (ResultSet rs = pStmt.executeQuery()) {
	            while (rs.next()) {
	                Product product = new Product(
	                    rs.getInt("ID"),
	                    rs.getString("NAME"),
	                    rs.getInt("PRICE")
	                );
	                productList.add(product);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }

	    return productList;
	}

}
