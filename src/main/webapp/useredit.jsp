<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>名前・部署名変更</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">

</head>
<body>
	<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/common/header.jsp" %>

	<div class="container">
		<h1>名前・部署名変更</h1>
		<form action="＃" method="post">
			<div class="form-group">
				<label>部署</label> <select name="department" required>
					<option value="">選択してください</option>
					<option value="総務部">総務部</option>
					<option value="営業部">営業部</option>
					<option value="商品開発部">商品開発部</option>
					<option value="研究開発部">研究開発部</option>
					<option value="技術部">技術部</option>
					<option value="情報システム部">情報システム部</option>
				</select>
			</div>
			<div class="form-group">
				<label for="name">名前</label> <input type="text" id="name"
					name="name" required>
			</div>


			<div class="btn-area">
				<button type="submit" class="action-btn">登録</button>
			</div>
		</form>
	</div>

	<footer>ごはん係DX</footer>
</body>
</html>
