<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>マイページ</title>
    <style>
        body {
            font-family: "メイリオ", sans-serif;
            margin: 0;
            background-color: #fff;
        }
        header {
            background-color: #fce58b;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            padding: 8px 16px;
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
            margin-bottom: 30px;
        }
        .summary {
            font-size: 18px;
            margin-bottom: 30px;
        }
        .highlight {
            background-color: #ffe699;
            padding: 2px 6px;
            border-radius: 4px;
        }
        ul {
            list-style: none;
            padding-left: 0;
            font-size: 16px;
        }
        ul li {
            margin: 12px 0;
        }
        ul li a {
            color: #000;
            text-decoration: underline;
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
    <div class="user-info">
        社員番号 ${employeeId}<br>
        部署名 ${department}　名前 ${name}
        <img src="images/rice_icon.png" alt="icon">
    </div>
</header>

<div class="container">
    <h1>マイページ</h1>
    <div class="summary">
        今月は <span class="highlight">${totalAmount}</span> 円分注文しました<br>
        今月の総カロリーは <span class="highlight">${totalCalories}</span> kcalです
    </div>

    <div class="menu">
        ＜マイメニュー＞
        <ul>
            <li><a href="${pageContext.request.contextPath}/UserNewOrderServlet">注文確認・注文キャンセル</a></li>
            <li><a href="${pageContext.request.contextPath}/OrderFormServlet">注文フォームへ</a></li>
            <li><a href="${pageContext.request.contextPath}/UserOrderListServlet">過去の注文履歴</a></li>
            <li><a href="${pageContext.request.contextPath}/ChangeUserdomainServlet">名前・部署名変更</a></li>
            <li><a href="${pageContext.request.contextPath}/UserMainServlet">ログアウト</a></li>
        </ul>
    </div>
</div>

<footer>ごはん係DX</footer>
</body>
</html>
