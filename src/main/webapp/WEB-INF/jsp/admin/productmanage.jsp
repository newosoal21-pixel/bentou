<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>メニューの登録・削除</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
 <%@ include file="/WEB-INF/jsp/common/header_admin.jsp" %>
<body>
  <main>
    <h1>メニューの登録・削除</h1>
    <ul>
  ・登録
  <div class="form-row">
    <!-- 左：画像 -->
    <div class="upload-container">
      <p>プレビュー画面</p>
      <img id="preview-image" src="${pageContext.request.contextPath}/img/placeholder.png" alt="プレビュー" />
      <input type="file" id="image-input" accept="image/*" />
    </div>

    <!-- 右：入力フォーム -->
    <div class="form-fields">
      <label>名前<input type="text" /></label>
      <label>金額<input type="text" /></label>
      <label>Kcal<input type="text" /></label>
      <button class="action">登録する</button>
    </div>
  </div>

        ・登録済み
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
            <tr>
              <td>1</td>
              <td>しゃけ弁当</td>
              <td>600円</td>
              <td>・・・・</td>
              <td><input type="checkbox" checked /></td>
              <td><input type="checkbox" /></td>
            </tr>
            <tr>
              <td>・・・・・</td>
              <td>・・・・・</td>
              <td>・・・・・</td>
              <td>・・・・・</td>
              <td><input type="checkbox" checked /></td>
              <td><input type="checkbox" /></td>
            </tr>
          </tbody>
        </table>
        <div class="actions">
        <button class="action">表示</button>
        <button class="action">削除</button>
        </div>
      </li>
    </ul>
  </main>
 <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
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

