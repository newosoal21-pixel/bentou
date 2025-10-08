package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import logic.ProductLogic;
import model.Product;

/**
 * Servlet implementation class ProductsManagementServlet
 */
@WebServlet("/ProductsManagementServlet")
@MultipartConfig(location = "/tmp", maxFileSize = 1048576, maxRequestSize = 5242880) // サイズは環境に合わせて調整してください
public class ProductsManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsManagementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Logicを実行し、商品リストを取得
        ProductLogic productLogic = new ProductLogic();
        List<Product> productList = productLogic.execute();
        
        // 取得したリストをリクエストスコープに格納
        request.setAttribute("productList", productList);
        
        // JSPへフォワード
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/admin/productmanage.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		String actionType = request.getParameter("actionType");
		
		// -----------------------------------------------------------------
		// 新規商品登録処理
		// -----------------------------------------------------------------
		if ("insertProduct".equals(actionType)) {
			
			String itemName = request.getParameter("itemName");
			String itemPriceStr = request.getParameter("itemPrice");
			String itemCalStr = request.getParameter("itemCal");
			Part filePart = request.getPart("imageFile"); // アップロードされたファイルを取得
			
			// 入力値のチェックと変換
			int itemPrice, itemCal;
			try {
				itemPrice = Integer.parseInt(itemPriceStr);
				itemCal = Integer.parseInt(itemCalStr);
			} catch (NumberFormatException e) {
				request.setAttribute("message", "金額またはKcalの値が不正です。");
				doGet(request, response);
				return;
			}
			
			// ファイル名生成と保存処理
			String submittedFileName = filePart.getSubmittedFileName();
			String imageFileName = null;
			if (submittedFileName != null && !submittedFileName.isEmpty()) {
			    // ファイル名が重複しないようUUIDを使用し、拡張子を保持
			    String extension = submittedFileName.substring(submittedFileName.lastIndexOf("."));
			    imageFileName = UUID.randomUUID().toString() + extension;
			    
			    // サーバーの保存先パス（/img/products/）。実際の環境に合わせてパスを修正してください
			    String savePath = getServletContext().getRealPath("/img/products/"); 
			    File saveDir = new File(savePath);
			    if (!saveDir.exists()) {
			        saveDir.mkdirs();
			    }
			    filePart.write(savePath + imageFileName);
			}
			
			// Productオブジェクトを作成
			Product newProduct = new Product(
			        0, // IDはAuto Incrementのため0
			        itemName, 
			        itemPrice, 
			        itemCal, 
			        imageFileName != null ? "img/products/" + imageFileName : null // DBに保存するパス
			);
			
			// Logicを呼び出し登録
			ProductLogic logic = new ProductLogic();
			if (logic.registerProduct(newProduct)) {
				request.setAttribute("message", itemName + "をメニューに登録しました。");
			} else {
				request.setAttribute("message", itemName + "の登録に失敗しました。");
			}
			
			// 登録後、商品リストを再取得して画面を更新
			doGet(request, response);
			return;
		}
		
		// -----------------------------------------------------------------
		// 既存のフラグ更新処理 (表示/削除ボタンによる個別更新)
		// -----------------------------------------------------------------
		if ("updateDisplay".equals(actionType) || "updateDelete".equals(actionType)) {
			
			ProductLogic productLogic = new ProductLogic();
			// 現在のDB値を保持するためにリストを先に取得
            List<Product> currentProductList = productLogic.execute();
            
            int updateCount = 0;
            String updatedFlagName = "";

            if ("updateDisplay".equals(actionType)) {
                updatedFlagName = "表示フラグ";
            } else {
                updatedFlagName = "削除フラグ";
            }
            
            for (Product product : currentProductList) {
            	int id = product.getId();
            	
            	String displayParam = request.getParameter("displayFlag_" + id);
            	String deleteParam = request.getParameter("deleteFlag_" + id);
            	
            	boolean newDisplayFlag = product.isDisplayFlag();
            	boolean newDeleteFlag = product.isDeleteFlag();
            	
            	boolean flagChanged = false; 
            	
            	if ("updateDisplay".equals(actionType)) {
            	    boolean submittedDisplayFlag = "on".equals(displayParam);
            	    if (product.isDisplayFlag() != submittedDisplayFlag) { // 値が変更されたかチェック
            	        newDisplayFlag = submittedDisplayFlag;
            	        flagChanged = true;
            	    }
            	    
            	} else if ("updateDelete".equals(actionType)) {
            	    boolean submittedDeleteFlag = "on".equals(deleteParam);
            	    if (product.isDeleteFlag() != submittedDeleteFlag) { // 値が変更されたかチェック
            	        newDeleteFlag = submittedDeleteFlag;
            	        flagChanged = true;
            	    }
            	}
            	
                // フラグに変更があった場合のみDB更新処理を実行
                if (flagChanged) {
                    int result = productLogic.updateProductFlags(id, newDisplayFlag, newDeleteFlag);
                    if (result > 0) {
                        updateCount++;
                    }
                }
            }
            
            // メッセージの分岐と格納
            if (updateCount > 0) {
                request.setAttribute("message", updateCount + "件の" + updatedFlagName + "を更新しました。");
            } else {
                request.setAttribute("message", "更新された商品はありませんでした。");
            }
            
            // 更新後、結果を反映させるためにdoGetを呼び出し、再表示
            doGet(request, response);
            
		} else {
		    // その他のPOSTリクエスト（予期しないactionType）
			doGet(request, response);
		}
	}
}