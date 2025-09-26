<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文確認・キャンセル</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header_admin.css">

<style>
/* ★★★ 画像デザインに合わせた新しいCSSを定義・適用 ★★★ */

/* 全体のコンテナ */
.container {
	width: 80%;
	margin: 20px auto;
	padding: 20px;
}

.subtitle {
	font-size: 1.1em;
	font-weight: bold;
	color: #555;
	margin-bottom: 25px;
}

/* 注文ブロック */
.order-block {
	/* 注文ブロックの見出し */
	font-size: 1.2em;
	font-weight: bold;
	margin-top: 20px;
	margin-bottom: 10px;
	color: #333;
}

/* 注文詳細テーブル */
.order-detail-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 15px;
}

.order-detail-table th, .order-detail-table td {
	border: 1px solid #e0e0e0;
	padding: 10px;
	text-align: left;
	font-size: 0.95em;
}

.order-detail-table th {
	background-color: #ffb84d; /* 画像のヘッダー色 */
	color: black;
	font-weight: bold;
}

/* 金額の行を右寄せにし、￥マークを付けるため右寄せ */
.order-detail-table td:nth-child(4) {
	text-align: right;
	font-weight: bold;
}

/* キャンセルボタンのコンテナ */
.cancel-btn-container {
	text-align: right; /* ボタンを右に寄せる */
	margin-bottom: 30px; /* 注文ブロック間のスペース */
}

.cancel-btn {
	background-color: #dc3545; /* 赤色（キャンセルを強調） */
	color: white;
	border: none;
	padding: 5px 10px;
	border-radius: 3px;
	cursor: pointer;
	font-size: 0.9em;
	/* 画像のデザインに合わせ、少し控えめなボタンにする */
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);

}

/* モーダル全体 */
.modal {
  display: none; /* 初期は非表示 */
  position: fixed;
  z-index: 9999;
  left: 0; top: 0;
  width: 100%; height: 100%;
  background-color: rgba(0,0,0,0.5); /* 背景を半透明 */
}

/* モーダルの中身 */
.modal-content {
  background: #fff;
  color: #f2c200;
  font-weight: bold;
  padding: 20px;
  border-radius: 10px;
  width: 300px;
  text-align: center;
  margin: 15% auto; /* 中央に配置 */
  box-shadow: 0 4px 10px rgba(0,0,0,0.3);
}

/* ボタン配置 */
.modal-buttons {
  margin-top: 15px;
  display: flex;
  justify-content: space-around;
}
.modal-btn:hover {
  background: #fdf5d1;       /* ホバー時の背景色 */
}

/* ボタン配置 */
.modal-buttons {
  margin-top: 15px;
  display: flex;
  justify-content: space-around;
}

/* === ここから追記 === */

/* 共通ボタンスタイル */
.modal-buttons button {
  padding: 8px 25px;
  font-size: 1em;
  font-weight: bold;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: 0.3s;
}

/* 戻るボタン */
#cancelNo {
  background-color: #ccc;
  color: #333;
}
#cancelNo:hover {
  background-color: #bbb;
}

/* OKボタン */
#cancelYes {
  background-color: #dc3545;
  color: #fff;
}
#cancelYes:hover {
  background-color: #b52a38;
}

.modal-buttons button:active {
  transform: scale(0.97);
}


/* 既存の未使用スタイルは削除 */
</style>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container">
		<h1>注文確認・キャンセル</h1>
		<div class="subtitle">＜現在の注文状況＞</div>

		<c:forEach var="orderBlock" items="${orderList}" varStatus="status">
			<div class="order-block">
				注文${status.index + 1}

				<table class="order-detail-table">
					<tr>
						<th>日付</th>
						<th>名前</th>
						<th>個数</th>
						<th>金額</th>
					</tr>

					<c:forEach var="item" items="${orderBlock.items}"
						varStatus="itemStatus">
						<tr>
							<td><c:if test="${itemStatus.first}">
                                ${item.formattedDate} 
                            </c:if></td>
							<td>${item.itemName}</td>
							<td>${item.quantity}</td>
							<td>￥${item.amount}</td>
						</tr>
					</c:forEach>
				</table>

				<div class="cancel-btn-container">
					<!-- キャンセルボタン -->
					<form action="UserNewOrderServlet" method="post"
						class="cancel-form">
						<input type="hidden" name="orders_id"
							value="${orderBlock.orderId}">
						<button type="button" class="cancel-btn"
							onclick="openModal(this.form)">この注文をキャンセルする</button>
					</form>

					<!-- モーダル（初期は非表示） -->
					<div id="confirmModal" class="modal">
						<div class="modal-content">
							<p>本当にキャンセルしてよろしいですか？</p>
							<div class="modal-buttons">
								<button id="cancelNo">戻る</button>
								<button id="cancelYes">OK</button>
							</div>
						</div>
					</div>


				</div>
			</div>
		</c:forEach>

		<c:if test="${empty orderList}">
			<p>本日分の注文履歴はありません。</p>
		</c:if>
	</div>

	<script>
	let targetForm = null;

	function openModal(form) {
	  targetForm = form; // 押されたフォームを保持
	  document.getElementById("confirmModal").style.display = "block";
	}

	document.getElementById("cancelNo").onclick = function() {
	  document.getElementById("confirmModal").style.display = "none";
	  targetForm = null;
	};

	document.getElementById("cancelYes").onclick = function() {
	  if (targetForm) targetForm.submit(); // OKならフォーム送信
	};

	</script>


	<%@ include file="/common/footer.jsp"%>
</body>
</html>