<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文完了</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>
<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/common/header.jsp" %>

<div class="main">
    <h1>注文完了</h1>
    <div class="message">
        注文が完了しました!<br>
        ご利用ありがとうございました！
    </div>
    <form action="LogoutServlet" method="post">
        <button type="submit" class="action-btn">ログアウト</button>
    </form>
</div>

<!-- フッター -->
  <%@ include file="/common/footer.jsp" %>
  
</body>
</html>
