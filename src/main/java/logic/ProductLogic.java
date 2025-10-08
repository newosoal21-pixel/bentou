package logic;

import java.util.List;

import dao.ProductDAO;
import model.Product;

public class ProductLogic {
	ProductDAO dao = new ProductDAO();

	public List<Product> findByDetail(Product p){	
		return dao.findByDetail(p);
	}
    // 全件取得用のメソッド
    public List<Product> execute(){
        return dao.findByDetail(); // DAOの引数なしのメソッドを呼び出す
    }
    // ★商品フラグ更新メソッドの追加
    /**
     * 商品の表示フラグと削除フラグを更新する
     * @param id 更新対象のproducts_id
     * @param displayFlag 表示フラグ (true/false)
     * @param deleteFlag 削除フラグ (true/false)
     * @return 更新されたレコード数
     */
    public int updateProductFlags(int id, boolean displayFlag, boolean deleteFlag) {
    	return dao.updateFlags(id, displayFlag, deleteFlag);
    }
    
    public boolean registerProduct(Product product) {
        return dao.insertProduct(product);
    }
}
