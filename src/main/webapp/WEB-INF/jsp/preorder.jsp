<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文確認</title>
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
            padding: 60px;
            text-align: center;
        }
        h1 {
            font-size: 28px;
            margin-bottom: 40px;
            text-align: left;
        }
        .order-list {
            text-align: left;
            font-size: 18px;
            margin-bottom: 30px;
        }
        .order-list li {
            margin: 12px 0;
        }
        .total {
            text-align: right;
            font-size: 20px;
            margin-top: 20px;
            font-weight: bold;
        }
        .action-btns {
            margin-top: 40px;
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
            background: #fce58b;
            text-align: center;
            padding: 5px;
            font-size: 12px;
            margin-top: 40px;
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
        <img src="images/rice_icon.png" alt="icon">
    </div>
</header>

<div class="container">
    <h1>あなたの注文</h1>
    <ul class="order-list">
        <c:forEach var="item" items="${orderItems}">
            <li>・${item.name}　･･･　${item.quantity}個　${item.price}円</li>
        </c:forEach>
    </ul>
    <div class="total">合計 ${totalAmount}円</div>

    <div class="action-btns">
        <form action="orderform.jsp" method="get" style="display:inline;">
            <button type="submit" class="action-btn">注文フォームへ戻る</button>
        </form>
        <form action="ConfirmOrderServlet" method="post" style="display:inline;">
            <button type="submit" class="action-btn">注文する</button>
        </form>
    </div>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
