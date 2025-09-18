<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>登録内容確認画面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>
<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/css/header.jsp" %>

<div class="container">
    <h1>登録内容確認画面</h1>
    <ul>
        <li>部署名：${department}</li>
        <li>社員番号：${employeeId}</li>
        <li>パスワード：＊＊＊＊＊＊</li>
        <li>名前：${name}</li>
    </ul>

    <div class="btn-area">
        <form action="register.jsp" method="get" style="display:inline;">
            <button type="submit" class="action-btn">キャンセル</button>
        </form>
        <form action="RegisterServlet" method="post" style="display:inline;">
            <!-- 隠しフィールドで値を送信 -->
            <input type="hidden" name="department" value="${department}">
            <input type="hidden" name="employeeId" value="${employeeId}">
            <input type="hidden" name="password" value="${password}">
            <input type="hidden" name="name" value="${name}">
            <button type="submit" class="action-btn">OK</button>
        </form>
    </div>
</div>

<!-- フッター -->
  <%@ include file="/css/footer.jsp" %>
  
</body>
</html>
