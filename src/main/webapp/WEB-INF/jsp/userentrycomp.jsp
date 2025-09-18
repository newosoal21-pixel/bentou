<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>登録完了</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>
<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/css/header.jsp" %>


<div class="welcome-area img">
    <!-- ごはんとしゃもじの画像 -->
    <img src="images/rice.png" alt="ごはんアイコン">
    
    <!-- 完了メッセージ -->
    <div class="message">登録完了しました</div>

    <!-- ログイン画面へ遷移 -->
    <form action="login.jsp" method="get">
        <button type="submit" class="action-btn">ログインID・パスワード入力へ</button>
    </form>
</div>

<!-- フッター -->
  <%@ include file="/css/footer.jsp" %>
</body>
</html>
