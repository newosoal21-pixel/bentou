<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>更新結果</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
<style>
  body {
    font-family: "メイリオ", sans-serif;
    background-color: #f9f9f9;
    text-align: center;
    padding: 60px 20px;
  }

  h1 {
    font-size: 28px;
    color: #333;
    margin-bottom: 30px;
  }

  .message {
    font-size: 20px;
    color: #007b43;
    margin-bottom: 50px;
  }

  .error {
    font-size: 20px;
    color: #c0392b;
    margin-bottom: 50px;
  }

  .btn-area a {
    display: inline-block;
    background-color: #ff9900;
    color: #fff;
    padding: 12px 28px;
    border-radius: 8px;
    text-decoration: none;
    font-weight: bold;
    transition: 0.3s;
  }

  .btn-area a:hover {
    background-color: #e68a00;
  }
</style>
</head>
<body>

  <!-- 共通ヘッダー呼び出し -->
  <%@ include file="/common/header.jsp" %>

  <div class="container">
    <h1>名前・部署名の更新結果</h1>

    <!-- Servletでセットした message を表示 -->
    <div class="<%= request.getAttribute("message").toString().contains("失敗") ? "error" : "message" %>">
      <%= request.getAttribute("message") %>
    </div>

    <div class="btn-area">
      <a href="${pageContext.request.contextPath}/UserEditServlet">← 戻る</a>
      <a href="${pageContext.request.contextPath}/UserMypageServlet">マイページへ</a>
    </div>
  </div>

  <footer>ごはん係DX</footer>
</body>
</html>
