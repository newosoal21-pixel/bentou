<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ようこそ画面</title>
  	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    
</head>
<body>


<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/common/header.jsp" %>

<div class="main">
  <div class="welcome-area">
    <!-- 左のアイコン -->
    <img src="${pageContext.request.contextPath}/images/rice.png" alt="ごはん係アイコン">

    <!-- 中央テキスト -->
    <div class="welcome-text">
      ようこそ！<br>${employee.userName}さん
    </div>
  </div>

  <!-- ボタン -->
  <div class="button-area">
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
      <button type="submit" class="action-btn">ログアウト</button>
    </form>
    <form action="${pageContext.request.contextPath}/OrderFormServlet" method="get">
      <button type="submit" class="action-btn">注文へ</button>
    </form>
  </div>
</div>




<!-- フッター -->
  <%@ include file="/common/footer.jsp" %>
  
</body>
</html>
