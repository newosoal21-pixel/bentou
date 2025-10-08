<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>メニューの登録・削除</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
 <%@ include file="/common/header_admin.jsp" %>
<body>
  <main>
    <h1>メニューの登録・削除</h1>
    
        <%-- ★メッセージ表示エリアの追加 --%>
    <c:if test="${not empty message}">
        <p style="color: blue;">${message}</p>
    </c:if>
    
    <ul>
  ・登録
   <form action="${pageContext.request.contextPath}/ProductsManagementServlet" method="post" enctype="multipart/form-data">
  <div class="form-row">
    <div class="upload-container">
      <p>プレビュー画面</p>
      <img id="preview-image" src="${pageContext.request.contextPath}/img/placeholder.png" alt="プレビュー" />
      <input type="file" id="image-input" name="imageFile" accept="image/*" required/>
    </div>

    <div class="form-fields">
      <label>名前<input type="text" name="itemName" required/></label>
      <label>金額<input type="text" name="itemPrice" required/></label>
      <label>Kcal<input type="text" name="itemCal" required/></label>
      <button class="action" type="submit" name="actionType" value="insertProduct">登録する</button>
    </div>
  </div>
  </form>

        ・登録済み
        <form action="${pageContext.request.contextPath}/ProductsManagementServlet" method="post">
        <table>
          <thead>
            <tr>
              <th>id</th>
              <th>名前</th>
              <th>金額</th>
              <th>kcal</th>
              <th>注文リストへ表示</th>
              <th>登録済みリストから削除</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="product" items="${productList}">
            <tr>
              <td><c:out value="${product.id}" /></td>
              <td><c:out value="${product.name}" /></td>
              <td><c:out value="${product.price}円" /></td>
              <td><c:out value="${product.cal}" /></td>
              <td>
                <input type="checkbox" 
                       name="displayFlag_${product.id}" 
                       value="on"
                       ${product.displayFlag ? 'checked' : ''} />
              </td>
              <td>
                <input type="checkbox" 
                       name="deleteFlag_${product.id}" 
                       value="on"
                       ${product.deleteFlag ? 'checked' : ''} />
              </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="actions">
        <button class="action" type="submit" name="actionType" value="updateDisplay">表示</button>
        <button class="action" type="submit" name="actionType" value="updateDelete">削除</button>
        </div>
        </form>
      </li>
    </ul>
  </main>
 <%@ include file="/common/footer.jsp" %>
</body>

<script>
  document.getElementById('image-input').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview-image').src = e.target.result;
    };
    reader.readAsDataURL(file);
  });
</script>
</html>