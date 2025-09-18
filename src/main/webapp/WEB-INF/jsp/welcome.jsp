<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ごはん係DX</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
</head>
<body>

  <!-- 共通ヘッダー呼び出し -->
  <%@ include file="/css/header_top.jsp" %>

  

  <!-- メイン -->
  <main class="main">
    <div class="welcome-text">ようこそ！<br>ごはん係DXへ</div>

    <div class="button-area">
      <a href="LoginServlet?next=new" class="action-btn">新規登録</a>
      <a href="LoginServlet?next=login" class="action-btn">ログイン</a>
    </div>
  </main>

  <!-- フッター -->
  <%@ include file="/css/footer.jsp" %>

</body>
</html>
