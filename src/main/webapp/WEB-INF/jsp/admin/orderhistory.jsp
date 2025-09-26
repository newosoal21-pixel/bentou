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
            <label for="year">年</label>
            <select id="year" name="year">
                <option value="">--</option>
                <c:forEach var="y" begin="2020" end="2025">
                    <option value="${y}" <c:if test="${param.year == y}">selected</c:if>>${y}</option>
                </c:forEach>
            </select>

            <label for="month">月</label>
            <select id="month" name="month">
                <option value="">--</option>
                <c:forEach var="m" begin="1" end="12">
                    <option value="${m}" <c:if test="${param.month == m}">selected</c:if>>${m}</option>
                </c:forEach>
            </select>

            <label for="day">日</label>
            <select id="day" name="day">
                <option value="">--</option>
                <c:forEach var="d" begin="1" end="31">
                    <option value="${d}" <c:if test="${param.day == d}">selected</c:if>>${d}</option>
                </c:forEach>
            </select>

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
            <button type="submit" formaction="downloadCsv.jsp">CSV DL</button>
        </div>
    </main>
  <%@ include file="/common/footer.jsp" %>
</body>
</html>
