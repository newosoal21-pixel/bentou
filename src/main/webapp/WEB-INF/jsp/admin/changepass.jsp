<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>パスワード変更 - ごはん係DX</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header_admin.jsp" %>
    <main>
        <h1>パスワード変更</h1>

        <form action="PasswordChangeServlet" method="post">
            <div class="form-group">
                ● 現在のパスワード
                <br>
                <input type="password" name="currentPassword" required>
            </div>

            <div class="form-group">
                ● 変更後のパスワード（4文字以上）
                <br>
                <input type="password" name="newPassword" minlength="4" required>
            </div>

            <div class="form-group">
                ● パスワード確認用（再度入力）
                <br>
                <input type="password" name="confirmPassword" required>
            </div>

            <button type="submit" class="button">変更する</button>
        </form>
    </main>
  <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
