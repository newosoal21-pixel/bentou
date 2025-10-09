<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

  <!-- メイン -->
  <div class="container">
    <form action="NewAduserServlet" method="post">
      <div class="form-group label">
      <label>部署</label>
      <select name="departmentId" required>
        <option value="">選択してください</option> 
        
        <c:forEach var="dept" items="${departmentList}">
            <option value="${dept.departmentId}">${dept.departmentName}</option>
        </c:forEach>
        </select>
      </div>

      <!-- 社員ID -->
      <div class="form-group label">
      <label>社員ID</label>
      <input type="text" name="employeesId" required>
      </div>

      <!-- パスワード -->
      <div class="form-group label">
      <label>パスワード <span style="font-weight: normal; font-size: 12px;">(4文字以上)</span></label>
      <input type="password" name="password" required>
      <% 
        String error = (String)request.getAttribute("errorMsg");
        if(error != null){
      %>
        <div class="error"><%= error %></div>
      <% } %>
      </div>

      <!-- 確認用パスワード -->
      <div class="form-group label">
      <label>確認用パスワード</label>
      <input type="password" name="passwordCheck" required>
      </div>

      <!-- 名前 -->
      <div class="form-group label">
      <label>名前</label>
      <input type="text" name="userName" required>
      </div>

      <!-- 登録ボタン -->
      <div class="form-group label">
      <button type="submit" class="btn">登録</button>
      </div>
    </form>
  </div>

<!-- フッター -->
  <%@ include file="/common/footer.jsp" %>

</body>
</html>
