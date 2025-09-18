<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>社員IDの削除 - ごはん係DX</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<%@ include file="/common/header_admin.jsp" %>
    <main>
        <h1>社員IDの削除</h1>

        <form action="UserDeleteServlet" method="get" class="search-bar">
            🔍 <input type="text" name="searchId" placeholder="（社員ID）">
            <button type="submit">検索</button>
        </form>

        <div class="table-container">
            <table>
                <tr>
                    <th>社員ID</th>
                    <th>名前</th>
                    <th></th>
                </tr>
                
                <c:forEach var="emp" items="${employeeList}">
    <tr>
        <td>${emp.employeesId}</td>
        <td>${emp.userName}</td>
        <td>
            <form method="post" action="UserDeleteServlet">
                <input type="hidden" name="id" value="${emp.employeesId}">
                <button type="submit" class="delete-button" onclick="return confirm('本当に削除しますか？');">削除</button>
            </form>
        </td>
    </tr>
</c:forEach>
            </table>
        </div>
    </main>
  <%@ include file="/common/footer.jsp" %>
</body>
</html>
