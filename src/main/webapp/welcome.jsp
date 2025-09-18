<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ごはん係DX</title>
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
      display: flex;
      justify-content: flex-end;
    }
    header a {
      text-decoration: underline;
      color: #000;
      font-size: 14px;
    }

    /* メイン */
    main {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 70vh;
      text-align: center;
    }
    h1 {
      font-size: 32px;
      color: #7a3e00;
      margin-bottom: 40px;
    }

    /* ボタン */
    .btn {
      display: inline-block;
      padding: 10px 25px;
      margin: 0 20px;
      border: 2px solid #f2c200;
      border-radius: 6px;
      background: #fff;
      color: #7a3e00;
      font-size: 16px;
      cursor: pointer;
      text-decoration: none;
      transition: 0.3s;
    }
    .btn:hover {
      background: #fdf5d1;
    }

    /* フッター */
    footer {
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
    <a href="LoginServlet?next=adminlogin">管理者の方はこちら</a>
  </header>

  <!-- メイン -->
  <main>
    <h1>ようこそ！<br>ごはん係DXへ</h1>
    <div>
      <a href="LoginServlet?next=new" class="btn">新規登録</a>
      <a href="LoginServlet?next=login" class="btn">ログイン</a>
    </div>
  </main>

  <!-- フッター -->
  <footer>
    ごはん係DX
  </footer>
</form>
</body>
</html>
