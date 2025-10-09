<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>社員IDと利用金額 - ごはん係DX</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<%@ include file="/common/header_admin.jsp" %>

<main>
    <h1>社員IDと利用金額</h1>

    <form class="search-bar" method="get" action="UserTotalBuyServlet">

        <label for="year"></label>
        
<select name="year" id="year">
    <c:forEach var="year" items="${yearList}">
        <option value="${year}" ${selectedYear == year ? 'selected' : ''}>
            ${year}年
        </option>
    </c:forEach>
</select>


<select name="month" id="month">
    <c:forEach var="od" items="${orderDates}">
        <c:if test="${od.year == selectedYear}">
            <option value="${od.month}" ${selectedMonth == od.month ? 'selected' : ''}>
                ${od.month}月
            </option>
        </c:if>
    </c:forEach>
</select>


        <button type="submit">検索</button>
    </form>

    <table>
        <tr>
            <th>社員ID</th>
            <th>名前</th>
            <th>利用金額</th>
        </tr>

        <c:forEach var="u" items="${usageList}">
            <tr>
                <td>${u.employeesId}</td>
                <td>${u.userName}</td>
                <td>${u.totalBuy} 円</td>
            </tr>
        </c:forEach>
    </table>

    <div class="actions">
        <button type="button" onclick="window.print()">印刷</button>
        <form method="post" action="${pageContext.request.contextPath}/UserTotalBuyServlet" style="display: inline;">
            <%-- ★★★ 処理を分岐させるための隠しパラメータを追加 ★★★ --%>
            <input type="hidden" name="action" value="csv">
            
            <%-- 選択されている年と月の値を hidden で送信 --%>
            <input type="hidden" name="year" value="${selectedYear}">
            <input type="hidden" name="month" value="${selectedMonth}">
            <button type="submit">CSV DL</button>
        </form>   
    </div>
</main>