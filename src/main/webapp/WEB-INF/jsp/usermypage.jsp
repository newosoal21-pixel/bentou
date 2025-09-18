<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>マイページ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>
<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/css/header.jsp" %>

<div class="container">
    <h1>マイページ</h1>
    <div class="summary">
        今月は <span class="highlight">${totalAmount}</span> 円分注文しました<br>
        今月の総カロリーは <span class="highlight">${totalCalories}</span> kcalです
    </div>

    <div class="menu">
        ＜マイメニュー＞
        <ul>
            <li><a href="orderCheck.jsp">注文確認・注文キャンセル</a></li>
            <li><a href="orderForm.jsp">注文フォームへ</a></li>
            <li><a href="orderHistory.jsp">過去の注文履歴</a></li>
            <li><a href="userEdit.jsp">名前・部署名変更</a></li>
            <li><a href="LogoutServlet">ログアウト</a></li>
        </ul>
    </div>
</div>

<!-- フッター -->
  <%@ include file="/css/footer.jsp" %>
</body>
</html>
