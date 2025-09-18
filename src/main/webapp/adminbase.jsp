<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ごはん係DX | 管理者ページ</title>
  <style>
    body {
      margin: 0;
      font-family: 'Yu Gothic', 'Hiragino Kaku Gothic ProN', Meiryo, sans-serif;
      background-color: white;
      color: #7a3e00; /* 基本文字色 */
    }

    header {
      background-color: #FFE79B;
      height: 60px;
      display: flex;
      justify-content: flex-end;
      align-items: center;
      padding: 0 20px;
      font-size: 14px;
    }

    main {
      min-height: calc(100vh - 120px);
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      padding: 40px 20px;
      text-align: center;
    }

    .welcome-container {
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 40px;
      flex-wrap: wrap;
    }

    .welcome-text h1 {
      font-size: 36px;
      margin-bottom: 10px;
    }

    .welcome-text h2 {
      font-size: 32px;
      margin-top: 0;
    }

    .welcome-image img {
      width: 100px;
      height: auto;
    }

    .buttons {
      display: flex;
      gap: 20px;
      margin-top: 40px;
      flex-wrap: wrap;
    }

    .button {
      padding: 10px 25px;
      font-size: 16px;
      border: 2px solid #FFC107;
      color: #7a3e00;
      background-color: transparent;
      border-radius: 8px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .button:hover {
      background-color: #FFE79B;
    }

    footer {
      background-color: #FFE79B;
      height: 60px;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 14px;
    }
  </style>
</head>
<body>

  <header>
    管理者用
  </header>

  <main>
    <div class="welcome-container">
      <div class="welcome-image">
        <!-- 画像パスは実際のファイルに置き換えてください -->
        <img src="spoon_rice.png" alt="しゃもじとごはんのイラスト">
      </div>
      <div class="welcome-text">
        <h1>ようこそ！</h1>
        <h2>管理者の〇〇さん</h2>
      </div>
    </div>

    <div class="buttons">
      <button class="button">ログアウト</button>
      <a href="orderform.jsp" class="button">発注画面へ</a>
      <a href="adcontrol.jsp" class="button">管理者用画面へ</a>
    </div>
  </main>

  <footer>
    ごはん係DX
  </footer>

</body>
</html>