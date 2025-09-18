<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文確認・キャンセル</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>
<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/css/header.jsp" %>

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

<!-- フッター -->
  <%@ include file="/css/footer.jsp" %>
</body>
</html>
