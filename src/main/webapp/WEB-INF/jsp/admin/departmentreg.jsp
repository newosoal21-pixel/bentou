<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>部署名の変更・追加</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<%@ include file="/common/header_admin.jsp" %>
<body>

  <main>
    <h1>部署名の管理</h1>
    <c:if test="${not empty message}">
        <p style="color: blue;">${message}</p>
    </c:if>

    <section>
      <h2>現在登録されている部署</h2>
      <table id="department-list-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>部署名</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="dept" items="${departmentList}">
            <tr>
              <td><c:out value="${dept.departmentId}" /></td>
              <td><c:out value="${dept.departmentName}" /></td>
              <td>
                <button class="action edit-btn" 
                        data-id="${dept.departmentId}" 
                        data-name="${dept.departmentName}">変更</button>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </section>
    
    <hr>
    
    <section class="addSection">
      <h2>部署名新規作成</h2>
      <form action="DepartmentManagementServlet" method="post">
          <label for="deptName">部署名</label>
          <input type="text" id="deptName" name="deptName" placeholder="例：営業部" required/>
          <button class="action" type="submit" name="actionType" value="insert">登録</button>
      </form>
    </section>
    
    <form id="update-form" action="DepartmentManagementServlet" method="post" style="display:none; margin-top: 20px; border: 1px solid #ccc; padding: 15px;">
        <h3>部署名変更</h3>
        <input type="hidden" name="actionType" value="update" />
        <label>ID: <input type="text" name="deptId" id="update-deptId" readonly /></label><br>
        <label>新部署名: <input type="text" name="updatedDeptName" id="update-deptName" required /></label>
        <button type="submit" class="action">確定</button>
        <button type="button" onclick="document.getElementById('update-form').style.display='none'">キャンセル</button>
    </form>
    
  </main>
  
  <script>
    // 変更ボタンが押されたら、編集フォームを表示するJavaScript
    document.querySelectorAll('.edit-btn').forEach(button => {
        button.addEventListener('click', function() {
            const id = this.getAttribute('data-id');
            const name = this.getAttribute('data-name');
            
            const form = document.getElementById('update-form');
            document.getElementById('update-deptId').value = id;
            document.getElementById('update-deptName').value = name;
            form.style.display = 'block';
            document.getElementById('update-deptName').focus();
        });
    });
  </script>
  
  <%@ include file="/common/footer.jsp" %>
</body>
</html>