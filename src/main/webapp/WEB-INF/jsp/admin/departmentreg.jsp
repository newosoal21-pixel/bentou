<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>部署の削除・追加</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<%@ include file="/common/header_admin.jsp" %>
<body>

  <main>
    <section>
      <h2>現在登録されている部署</h2>
      <table>
        <thead>
          <tr>
            <th>部署名</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>開発部</td>
            <td><button class="action">削除</button></td>
          </tr>
          <tr>
            <td>技術部</td>
            <td><button class="action">削除</button></td>
          </tr>
          <tr>
            <td>・・・・</td>
            <td><button class="action">削除</button></td>
          </tr>
        </tbody>
      </table>
    </section>
    <section class="addSection">
      <h2>部署新規作成</h2>
      <label for="deptName">部署名</label>
      <input type="text" id="deptName" placeholder="部" />
      <button class="action">登録</button>
    </section>
  </main>
  <%@ include file="/common/footer.jsp" %>
</body>
</html>
