<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>
<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/common/header2.jsp" %>

<div class="container">
    <h1>ログイン</h1>
    <form action="LoginServlet" method="post">
        <div class="form-group label">
            <label for="employeesId">社員番号 (User ID)</label>
            <input type="text" id="employeesId" name="employeesId" required>
        </div>
        <div class="form-group label">
            <label for="password">パスワード</label>
            <input type="password" id="password" name="password" required>
        </div>

        <!-- エラーメッセージ（サーブレットから渡された時だけ表示） -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="error"><%= errorMessage %></div>
        <%
            }
        %>

        <div class="btn-area">
            <button type="button" class="action-btn" onclick="location.href='welcome.jsp'">キャンセル</button>
            <button type="submit" class="action-btn" name="button" value="user">ログイン</button>
        </div>
    </form>
</div>

<!-- フッター -->
  <%@ include file="/common/footer.jsp" %>
  
</body>
</html>
