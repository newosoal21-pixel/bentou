<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文確認・キャンセル</title>
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
            border: 2px solid #000;
            border-radius: 6px;
            background: #fff;
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
            padding: 40px 60px;
        }
        h1 {
            font-size: 26px;
            margin-bottom: 10px;
        }
        .subtitle {
            font-size: 14px;
            color: #555;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 16px;
            margin-bottom: 20px;
        }
        th {
            background-color: #ffcc00;
            padding: 8px;
            text-align: left;
        }
        td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }
        tr:nth-child(even) td {
            background-color: #fff5e6;
        }
        .cancel-btn {
            margin-top: 8px;
            padding: 8px 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background: #f9f9f9;
            color: #7a3e00;
            font-weight: bold;
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
    <h1>注文確認・キャンセル</h1>
    <div class="subtitle">＜現在の注文状況＞</div>

    <!-- JSTLで注文リストをループ -->
    <c:forEach var="order" items="${orderList}" varStatus="status">
        <div class="order-block">
            注文${status.index + 1}
            <table>
                <tr>
                    <th>日付</th>
                    <th>名前</th>
                    <th>個数</th>
                    <th>金額</th>
                </tr>
                <c:forEach var="item" items="${order.items}">
                    <tr>
                        <td>${item.date}</td>
                        <td>${item.itemName}</td>
                        <td>${item.quantity}</td>
                        <td>￥${item.amount}</td>
                    </tr>
                </c:forEach>
            </table>

            <form action="CancelOrderServlet" method="post">
                <input type="hidden" name="orderId" value="${order.id}">
                <button type="submit" class="cancel-btn">この注文をキャンセルする</button>
            </form>
        </div>
    </c:forEach>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
