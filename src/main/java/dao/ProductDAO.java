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
		// ★SQLにdisplay_flag, delete_flagを追加し、削除されていない商品のみを取得
		String sql = "SELECT products_id, itemname, price, cal, image, display_flag, delete_flag FROM PRODUCTS WHERE delete_flag = 0 ORDER BY products_id";

		try (Connection conn = DBManager.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery()) {

			while (rs.next()) {
				// ★新しいコンストラクタを使用し、display_flagとdelete_flagをセット
				Product product = new Product(
						rs.getInt("products_id"),
						rs.getString("itemname"),
						rs.getInt("price"),
						rs.getInt("cal"),
						rs.getString("image"),
						// DBから取得した1/0をbooleanに変換
						rs.getBoolean("display_flag"),
						rs.getBoolean("delete_flag"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
		return productList;

	}
    
    // ★商品フラグ更新メソッドの追加
    /**
     * 商品の表示フラグと削除フラグを更新する
     * @param id 更新対象のproducts_id
     * @param displayFlag 表示フラグ (true=1, false=0)
     * @param deleteFlag 削除フラグ (true=1, false=0)
     * @return 更新されたレコード数
     */
    public int updateFlags(int id, boolean displayFlag, boolean deleteFlag) {
        String sql = "UPDATE PRODUCTS SET display_flag = ?, delete_flag = ? WHERE products_id = ?";
        int result = 0;

        try (Connection conn = DBManager.getConnection();
                PreparedStatement pStmt = conn.prepareStatement(sql)) {

            // booleanをDBのINT(1)またはTINYINT(1)に対応させる
            pStmt.setBoolean(1, displayFlag);
            pStmt.setBoolean(2, deleteFlag);
            pStmt.setInt(3, id);

            result = pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean insertProduct(Product product) {
        String sql = "INSERT INTO PRODUCTS (image, itemname, price, cal, display_flag, delete_flag) VALUES (?, ?, ?, ?, 1, 0)";
        
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setString(1, product.getImagePath());
            pStmt.setString(2, product.getName());
            pStmt.setInt(3, product.getPrice());
            pStmt.setInt(4, product.getCal());
            
            int result = pStmt.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
