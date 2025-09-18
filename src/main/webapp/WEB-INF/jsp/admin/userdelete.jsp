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

<%@ include file="/WEB-INF/jsp/common/header_admin.jsp" %>
    <main>
        <h1>社員IDの削除</h1>

        <form action="deleteEmployee.jsp" method="get" class="search-bar">
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
                <!-- JSTLで社員一覧をループ表示 -->
                <c:forEach var="emp" items="${employeeList}">
                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.name}</td>
                        <td>
                            <form method="post" action="deleteEmployeeAction.jsp">
                                <input type="hidden" name="id" value="${emp.id}">
                                <button type="submit" class="delete-button">削除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
