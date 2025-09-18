<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>管理者用画面 | ごはん係DX</title>
  <style>
    body {
      margin: 0;
      font-family: 'Yu Gothic', 'Hiragino Kaku Gothic ProN', Meiryo, sans-serif;
      background-color: white;
      color: #7a3e00;
    }

    header {
      background-color: #FFE79B;
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      padding: 10px 20px;
      position: relative;
    }

    .back-button {
      background-color: white;
      border: 2px solid #7a3e00;
      color: #7a3e00;
      padding: 6px 12px;
      border-radius: 8px;
      font-size: 14px;
      cursor: pointer;
    }

    .back-button:hover {
      background-color: #fff8e1;
    }

    .user-info {
      text-align: right;
      font-size: 14px;
    }

    .user-info img {
      width: 60px;
      vertical-align: middle;
      margin-left: 10px;
    }

    main {
      padding: 40px 10%;
    }

    h1 {
      font-size: 28px;
      margin-bottom: 30px;
    }

    ul {
      list-style-type: disc;
      padding-left: 1.5em;
    }

    ul li {
      margin-bottom: 15px;
      font-size: 18px;
    }

    ul li a {
      text-decoration: underline;
      color: #7a3e00;
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
    <button class="back-button">管理者用画面に戻る</button>
    <div class="user-info">
      管理者用<br>
      社員ID 000-0000-0000<br>
      名前 ○○△△
      <!-- イラスト画像は仮のパス -->
      <img src="spoon_rice.png" alt="ごはんイラスト">
    </div>
  </header>

  <main>
    <h1>管理者用画面</h1>
    <ul>
      <li><a href="#">注文時間の変更</a></li>
      <li><a href="#">社員IDと利用金額</a></li>
      <li><a href="#">パスワード変更</a></li>
      <li><a href="#">ログイン履歴</a></li>
      <li><a href="#">社員IDの削除</a></li>
      <li><a href="#">メニューの登録・削除</a></li>
      <li><a href="#">発注履歴</a></li>
      <li><a href="#">部署の変更・追加</a></li>
    </ul>
  </main>

  <footer>
    ごはん係DX
  </footer>

</body>
</html>