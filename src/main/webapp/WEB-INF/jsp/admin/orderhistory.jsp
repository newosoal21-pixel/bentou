<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8" />
    <title>発注履歴 - ごはん係DX</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<%@ include file="/common/header_admin.jsp" %>
<body>

    <main>
        <h1>発注履歴</h1>

        <form class="search-bar" method="get" action="AdorderHistoryServlet" >
            
            <select id="year" name="year">
                <option value="">--</option>
                <c:forEach var="y" begin="2020" end="2025">
                    <option value="${y}" <c:if test="${param.year == y}">selected</c:if>>${y}</option>
                </c:forEach>
            </select>
            <label for="year">年</label>

            
            <select id="month" name="month">
                <option value="">--</option>
                <c:forEach var="m" begin="1" end="12">
                    <option value="${m}" <c:if test="${param.month == m}">selected</c:if>>${m}</option>
                </c:forEach>
            </select>
			<label for="month">月</label>
            
            <select id="day" name="day">
                <option value="">--</option>
                <c:forEach var="d" begin="1" end="31">
                    <option value="${d}" <c:if test="${param.day == d}">selected</c:if>>${d}</option>
                </c:forEach>
            </select>
            <label for="day">日</label>

            <button type="submit" >検索</button>
        </form>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>日付</th>
                        <th>商品名</th>
                        <th>個数</th>
                        <th>金額</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${totalOrderDateList}">
                        <tr>
                            <td>${order.orderDate}</td>
                            <td>${order.itemName}</td>
                            <td>${order.totalQuantity}個</td>
                            <td>${order.totalPrice}円</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty orderList}">
                        <tr>
                            <td colspan="4" style="text-align:center;">データがありません</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

         <div class="actions">
            <button type="button" onclick="window.print()">印刷</button>
             <form method="post" action="${pageContext.request.contextPath}/AdorderHistoryServlet" style="display: inline;">
            <%-- 処理を分岐させるための隠しパラメータ --%>
            <input type="hidden" name="action" value="csv">
            
            <%-- 現在の検索条件を hidden で送信 --%>
            <input type="hidden" name="year" value="${param.year}">
            <input type="hidden" name="month" value="${param.month}">
            <input type="hidden" name="day" value="${param.day}">
            
            <button type="submit">CSV DL</button>
        </form>
        </div>
    </main>
  <%@ include file="/common/footer.jsp" %>
</body>
</html>
