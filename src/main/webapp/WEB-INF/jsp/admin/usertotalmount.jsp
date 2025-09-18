<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>社員IDと利用金額 - ごはん係DX</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<body>
<%@ include file="/common/header_admin.jsp" %>

    <main>
        <h1>社員IDと利用金額</h1>

        <form class="search-bar" method="get" action="usageList.jsp">
    <label for="year">年</label>
    <select name="year" id="year">
        <option value="2025">2025年</option>
        <option value="2024">2024年</option>
    </select>

    <label for="month">月</label>
    <select name="month" id="month">
        <% for (int m = 1; m <= 12; m++) { %>
            <option value="<%= m %>"><%= m %>月</option>
        <% } %>
    </select>

    <!-- ✅ ここでクラスを外す -->
    <button type="submit">検索</button>
</form>

        <table>
            <tr>
                <th>社員ID</th>
                <th>名前</th>
                <th>利用金額</th>
            </tr>
            <tr>
                <td>123-456-789</td>
                <td>山田太郎</td>
                <td>12345円</td>
            </tr>
            <!-- 追加データはここに繰り返し表示 -->
            <tr>
                <td>・・・・・・</td>
                <td>・・・・・・</td>
                <td>・・・・・・</td>
            </tr>
        </table>

        <div class="actions">
            <button type="button" onclick="window.print()">印刷</button>
            <button type="submit" formaction="downloadCsv.jsp">CSV DL</button>
        </div>
    </main>

  <%@ include file="/common/footer.jsp" %>
</body>
</html>
