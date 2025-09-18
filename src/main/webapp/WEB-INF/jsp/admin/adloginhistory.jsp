<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン履歴 - ごはん係DX</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
 <%@ include file="/WEB-INF/jsp/common/header_admin.jsp" %>

    <main>
        <h1>ログイン履歴</h1>

        <table>
            <tr>
                <th>社員ID</th>
                <th>名前</th>
                <th>年月日</th>
            </tr>
            <tr>
                <td>123-456-789</td>
                <td>山田太郎</td>
                <td>0000/00/00</td>
            </tr>
            <!-- 以下、JSTLやJavaでデータ繰り返し可 -->
        </table>
         <div class="actions">
            <button type="button" onclick="window.print()">印刷</button>
            <button type="submit" formaction="downloadCsv.jsp">CSV DL</button>
        </div>
 
    </main>

  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
