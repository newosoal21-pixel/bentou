<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ごはん係DX</title>
  <style>
    body {
      margin: 0;
      font-family: 'Yu Gothic', 'Hiragino Kaku Gothic ProN', Meiryo, sans-serif;
      background-color: white;
    }

    /* 上部バー */
    header {
      background-color: #FFE79B;
      height: 60px;
      display: flex;
      justify-content: flex-end;
      align-items: center;
      padding: 0 20px;
      font-size: 14px;
    }

    /* メインコンテンツ */
    main {
      height: calc(100vh - 120px);
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-align: center;
    }

    h1 {
      font-size: 36px;
      color: #7a3e00;
      margin: 0;
      line-height: 1.5;
    }

    /* ログインボタン */
    .login-button {
      margin-top: 40px;
      padding: 10px 30px;
      font-size: 16px;
      border: 2px solid #FFC107;
      color: #7a3e00;
      background-color: transparent;
      border-radius: 8px;
      cursor: pointer;
    }

    .login-button:hover {
      background-color: #FFE79B;
    }

    /* フッター */
    footer {
      background-color: #FFE79B;
      height: 60px;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 14px;
      color: #7A3E1D;
    }
  </style>
</head>
<body>

  <header>
    管理者用
  </header>

  <main>
    <h1>ようこそ！<br>ごはん係DXへ</h1>
    <button class="login-button">ログイン</button>
  </main>

  <footer>
    ごはん係DX
  </footer>

</body>
</html>