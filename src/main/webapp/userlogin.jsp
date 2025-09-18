<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
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
        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px 40px;
            background: #fff;
            border-radius: 8px;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
            font-size: 16px;
        }
        .form-group label {
            display: inline-block;
            width: 150px;
        }
        input[type="text"], input[type="password"] {
            width: 250px;
            padding: 6px;
            font-size: 14px;
        }
        .error {
            color: red;
            font-size: 14px;
            margin-top: -10px;
            margin-bottom: 20px;
        }
        .btn-area {
            text-align: center;
        }
        .action-btn {
            display: inline-block;
            margin: 0 30px;
            padding: 10px 25px;
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
            margin-top: 50px;
        }
    </style>
</head>
<body>
<header></header>

<div class="container">
    <h1>ログイン</h1>
    <form action="LoginServlet" method="post">
        <div class="form-group">
            <label for="userId">社員番号 (User ID)</label>
            <input type="text" id="userId" name="userId" required>
        </div>
        <div class="form-group">
            <label for="password">パスワード</label>
            <input type="password" id="password" name="password" required>
        </div>

        <!-- エラーメッセージ（サーブレットから渡された時だけ表示） -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="error"><%= errorMessage %></div>
        <%
            }
        %>

        <div class="btn-area">
            <button type="button" class="action-btn" onclick="location.href='welcome.jsp'">キャンセル</button>
            <button type="submit" class="action-btn">ログイン</button>
        </div>
    </form>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
