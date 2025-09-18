<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>登録完了</title>
    <style>
        body {
            font-family: "メイリオ", sans-serif;
            margin: 0;
            background-color: #fff;
        }
        header, footer {
            background-color: #fce58b;
            padding: 10px;
        }
        .main {
            text-align: center;
            padding: 80px 20px;
        }
        .main img {
            width: 100px;
            margin-bottom: 20px;
        }
        .message {
            font-size: 32px;
            color: #804000;
            margin: 20px 0 40px 0;
        }
        .action-btn {
            display: inline-block;
            padding: 12px 28px;
            border: 2px solid #ffcc00;
            border-radius: 6px;
            color: #ff9900;
            font-weight: bold;
            text-decoration: none;
            background: #fff;
            cursor: pointer;
        }
        footer {
            text-align: center;
            font-size: 12px;
            margin-top: 40px;
        }
    </style>
</head>
<body>
<header></header>

<div class="main">
    <!-- ごはんとしゃもじの画像 -->
    <img src="images/rice_set.png" alt="ごはんアイコン">
    
    <!-- 完了メッセージ -->
    <div class="message">登録完了しました</div>

    <!-- ログイン画面へ遷移 -->
    <form action="LoginServlet?next=login" method="get">
        <button type="submit" class="action-btn">ログインID・パスワード入力へ</button>
    </form>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
