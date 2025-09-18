<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文確認</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>

<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/css/header.jsp" %>

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

<!-- フッター -->
  <%@ include file="/css/footer.jsp" %>
  
</body>
</html>
