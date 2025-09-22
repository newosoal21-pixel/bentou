package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
			where.append("products_id = ?");
		}
		if (p.getName() != null && !p.getName().isEmpty()) {
			if (where.length() > 0)
				where.append("AND ");
			where.append("itemname LIKE ?");
		}
		if (p.getPrice() != 0) {
			if (where.length() > 0)
				where.append("AND ");
			where.append("price = ?");
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
							rs.getInt("products_id"),
							rs.getString("itemname"),
							rs.getInt("price"),
							rs.getInt("cal"),
							rs.getString("image"));
					productList.add(product);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

		return productList;
	}

	public List<Product> findByDetail() {
		List<Product> productList = new ArrayList<>();
		String sql = "SELECT * FROM PRODUCTS";

		try (Connection conn = DBManager.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery()) {

			while (rs.next()) {
				Product product = new Product(
						rs.getInt("products_id"),
						rs.getString("itemname"),
						rs.getInt("price"),
						rs.getInt("cal"),
						rs.getString("image"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
		return productList;

	}
	public int findProductIdByName(String name) {
	    String sql = "SELECT products_id FROM products WHERE itemname = ?";
	    try (Connection conn = DBManager.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, name);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("products_id");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1; // 見つからない場合
	}
}
