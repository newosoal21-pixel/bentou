<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文完了</title>
    <style>
        body {
            font-family: "メイリオ", sans-serif;
            margin: 0;
            background-color: #fff;
        }
        header {
            background-color: #fce58b;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 8px 16px;
        }
        .btn-back {
            padding: 6px 14px;
            border: 2px solid #ffcc00;
            border-radius: 6px;
            background: #fff;
            color: #ff9900;
            font-weight: bold;
            cursor: pointer;
        }
        .user-info {
            font-size: 14px;
            text-align: right;
        }
        .user-info img {
            width: 30px;
            vertical-align: middle;
            margin-left: 6px;
        }
        .container {
            padding: 80px;
            text-align: center;
        }
        h1 {
            font-size: 28px;
            margin-bottom: 40px;
            text-align: left;
        }
        .message {
            font-size: 22px;
            margin-bottom: 60px;
            line-height: 2em;
        }
        .logout-btn {
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
            background: #fce58b;
            text-align: center;
            padding: 5px;
            font-size: 12px;
            margin-top: 60px;
        }
    </style>
</head>
<body>
<header>
    <form action="usermypage.jsp" method="get">
        <button type="submit" class="btn-back">マイページへ戻る</button>
    </form>
    <div class="user-info">
        社員番号 ${employeeId}<br>
        部署名 ${department}　名前 ${name}
        <img src="images/gohan_icon.png" alt="icon" class="icon">
    </div>
</header>

<div class="container">
    <h1>注文完了</h1>
    <div class="message">
        注文が完了しました<br>
        ご利用ありがとうございました！
    </div>
    <form action="LogoutServlet" method="post">
        <button type="submit" class="logout-btn">ログアウト</button>
    </form>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
