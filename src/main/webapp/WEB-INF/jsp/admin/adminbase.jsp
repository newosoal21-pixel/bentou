<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ごはん係DX | 管理者ページ</title>

 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminbase.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
</head>
<body>
<%@ include file="/common/header_adminbase.jsp" %>
  <main>
  <div class="welcome-text">
    <h1>ようこそ！</h1>
    <h2>管理者の${userName}さん</h2>
  </div>

  <div class="buttons">
    <a href="logout.jsp" class="button">ログアウト</a>
    <a href="AdminOrderServlet" class="button">発注画面へ</a>
    <a href="AdminMainServlet" class="button">管理者用画面へ</a>
  </div>
</main>

<%@ include file="/common/footer.jsp" %>

</body>
</html>