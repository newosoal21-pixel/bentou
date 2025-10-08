<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>管理者用画面 | ごはん係DX</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

  <%@ include file="/common/header_admin.jsp" %>

  <main>
    <h1>管理者用画面</h1>
    <ul>
      <li><a href="${pageContext.request.contextPath}/OrderDeadlineServlet">注文時間の変更</a></li>
      <li><a href="${pageContext.request.contextPath}/UserTotalBuyServlet">社員IDと利用金額</a></li>
      <li><a href="${pageContext.request.contextPath}/PasswordChangeServlet">パスワード変更</a></li>
      <li><a href="${pageContext.request.contextPath}/LoginHistoryServlet">ログイン履歴</a></li>
      <li><a href="${pageContext.request.contextPath}/UserDeleteServlet">社員IDの削除</a></li>
      <li><a href="${pageContext.request.contextPath}/ProductsManagementServlet">メニューの登録・削除</a></li>
      <li><a href="${pageContext.request.contextPath}/AdorderHistoryServlet">発注履歴</a></li>
      <li><a href="${pageContext.request.contextPath}/DepartmentManagementServlet">部署の変更・追加</a></li>
    </ul>
  </main>

  <%@ include file="/common/footer.jsp" %>

</body>
</html>
