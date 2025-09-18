<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>登録内容確認画面</title>
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
            border-radius: 8px;
            background: #fff;
        }
        h1 {
            font-size: 22px;
            margin-bottom: 20px;
        }
        ul {
            list-style: disc;
            padding-left: 20px;
            margin-bottom: 30px;
            font-size: 16px;
        }
        li {
            margin-bottom: 12px;
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
    <h1>登録内容確認画面</h1>
    <ul>
        <li>部署名：${department}</li>
        <li>社員番号：${employeeId}</li>
        <li>パスワード：＊＊＊＊＊＊</li>
        <li>名前：${name}</li>
    </ul>

    <div class="btn-area">
        <form action="register.jsp" method="get" style="display:inline;">
            <button type="submit" class="action-btn">キャンセル</button>
        </form>
        <form action="RegisterServlet" method="post" style="display:inline;">
            <!-- 隠しフィールドで値を送信 -->
            <input type="hidden" name="department" value="${department}">
            <input type="hidden" name="employeeId" value="${employeeId}">
            <input type="hidden" name="password" value="${password}">
            <input type="hidden" name="name" value="${name}">
            <button type="submit" class="action-btn">OK</button>
        </form>
    </div>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
