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
        <li>部署名：${employee.departmentName}</li>
        <li>社員番号：${employee.employeesId}</li>
        <li>パスワード：＊＊＊＊＊＊</li>
        <li>名前：${employee.userName}</li>
    </ul>

    <div class="btn-area">
         <form action="LoginServlet" method="get" style="display:inline;">
            <button type="submit" name="action" value="cancel"  class="action-btn">キャンセル</button>
        </form>
   
        <form action="NewAduserServlet" method="post" style="display:inline;">
            <!-- 隠しフィールドで値を送信 -->
            <input type="hidden" name="departmentName" value="${employee.departmentName}">
            <input type="hidden" name="departmentId" value="${employee.departmentId}">
            <input type="hidden" name="employeesId" value="${employee.employeesId}">
            <input type="hidden" name="password" value="${employee.password}">
            <input type="hidden" name="userName" value="${employee.userName}">
            <button type="submit" name="button" value="OK" class="action-btn">OK</button>
        </form>
    </div>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
