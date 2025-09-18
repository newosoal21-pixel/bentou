<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>新規登録 - ごはん係DX</title>
  <style>
    body {
      margin: 0;
      font-family: "游ゴシック", "Hiragino Kaku Gothic ProN", sans-serif;
      background-color: #fff;
    }

    /* ヘッダー */
    header {
      background-color: #ffefb7;
      padding: 10px 20px;
    }
    header h2 {
      margin: 0;
      font-size: 20px;
    }

    /* メイン */
    main {
      padding: 40px;
      display: flex;
      justify-content: center;
    }
    form {
      background: #fff;
      padding: 20px 40px;
      border-radius: 8px;
    }
    h1 {
      font-size: 28px;
      margin-bottom: 20px;
    }
    label {
      display: block;
      margin: 15px 0 5px;
      font-weight: bold;
    }
    input, select {
      width: 300px;
      padding: 8px;
      font-size: 14px;
      border: 1px solid #333;
      border-radius: 4px;
    }
    .error {
      color: red;
      font-size: 12px;
      margin-top: 3px;
    }

    /* 登録ボタン */
    .btn {
      display: inline-block;
      margin-top: 20px;
      padding: 8px 20px;
      border: 2px solid #f2c200;
      border-radius: 6px;
      background: #fff;
      color: #7a3e00;
      font-size: 14px;
      cursor: pointer;
      text-decoration: none;
      transition: 0.3s;
    }
    .btn:hover {
      background: #fdf5d1;
    }

    /* フッター */
    footer {
      margin-top: 40px;
      background-color: #ffefb7;
      text-align: center;
      padding: 10px;
      font-size: 12px;
      color: #333;
    }
  </style>
</head>
<body>

  <!-- ヘッダー -->
  <header>
    
  </header>

  <!-- メイン -->
  <main>
    <form action="NewAduserServlet" method="post">
      <!-- 部署 -->
      <label>部署</label>
      <select name="department" required>
        <option value="">選択してください</option>
       
       <input type="hidden" name="総務部" value="1">
       <!-- 
        <option value="総務部">総務部</option>
        <option value="営業部">営業部</option>
        <option value="商品開発部">商品開発部</option>
        <option value="研究開発部">研究開発部</option>
        <option value="技術部">技術部</option>
        <option value="情報システム部">情報システム部</option>
      </select>

      <!-- 社員ID -->
      <label>社員ID</label>
      <input type="text" name="userId" required>

      <!-- パスワード -->
      <label>パスワード <span style="font-weight: normal; font-size: 12px;">(4文字以上)</span></label>
      <input type="password" name="password" required>
      <% 
        String error = (String)request.getAttribute("errorMsg");
        if(error != null){
      %>
        <div class="error"><%= error %></div>
      <% } %>

      <!-- 確認用パスワード -->
      <label>確認用パスワード</label>
      <input type="password" name="passwordCheck" required>

      <!-- 名前 -->
      <label>名前</label>
      <input type="text" name="name" required>

      <!-- 登録ボタン -->
      <button type="submit" class="btn">登録</button>
    </form>
  </main>

  <!-- フッター -->
  <footer>
    ごはん係DX
  </footer>

</body>
</html>
