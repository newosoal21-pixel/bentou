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
 <%@ include file="/common/header_admin.jsp" %>

    <main>
        <h1>ログイン履歴</h1>

	<section class="loginHistory" Items="${loginHisList}">
        <table>
            <tr>
                <th>社員ID</th>
                <th>名前</th>
                <th>年月日</th>
            </tr>
           <tbody>
					<c:forEach var="login" items="${loginHisList}">
						<tr>
							<td>${login.employeesId}</td>
							<td>${login.userName}</td>
							<td>${login.loginTimeStr}</td>
						</tr>
					</c:forEach>
				</tbody>
            <!-- 以下、JSTLやJavaでデータ繰り返し可 -->
        </table>
	</section>
         <div class="actions">
            <button type="button" onclick="window.print()">印刷</button>
            <button type="submit" formaction="downloadCsv.jsp">CSV DL</button>
        </div>
 
    </main>

  <%@ include file="/common/footer.jsp" %>
</body>
</html>
