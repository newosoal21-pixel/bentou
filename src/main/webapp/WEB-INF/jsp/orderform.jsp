<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文画面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header_admin.css">
<style>
.button-area {
	text-align: center;
	margin-top: 20px;
}

.button-area form {
	display: inline-block;
	margin: 0 10px;
}

.product img {
	width: 100%;
	max-width: 200px;
	height: auto;
	border-radius: 8px;
	object-fit: cover;
}
</style>
</head>
<body>

	<!-- 共通ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<main>
		<!-- タイトルと日付 -->
		<div class="form-header">
			<h1>注文フォーム</h1>
			<div class="order-date">0000/00/00</div>
		</div>

		<!-- 締め切りまでの時間 -->
		<p id="deadline" class="deadline">締め切りまであと00:00:00！！</p>

		<!--     商品一覧と注文ボタン（同じフォーム） -->
		<form method="post" action="OrderFormServlet">
			<div class="product-list">
				<c:forEach var="product" items="${productList}">
					<div class="product">
						<img src="${pageContext.request.contextPath}/${product.imagePath}"
							alt="${product.name}">
						<div class="product-name">${product.name}</div>
						<div class="product-info">${product.price}円（${product.cal}kcal）</div>
						<div class="quantity-control">
							<button type="button" class="minus">−</button>
							<input type="number" value="0" min="0" max="20"
								name="${product.id}">
							<button type="button" class="plus">＋</button>
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="button-area">
				<!--         注文ボタン（POST） -->
				<button type="submit" class="action-btn" id="order-button">注文する</button>
				<button type="button" class="action-btn"
					onclick="location.href='UserMainServlet'">戻る</button>


			</div>
		</form>
	</main>
	
	<!-- JavaScript（数量増減・締め切りカウントダウン） -->
	  <script>
	    document.addEventListener('DOMContentLoaded', () => {
	      document.querySelectorAll('.product').forEach(product => {
	        const minusBtn = product.querySelector('.minus');
	        const plusBtn = product.querySelector('.plus');
	        const input = product.querySelector('input');

	        minusBtn.addEventListener('click', () => {
	          let value = parseInt(input.value, 10);
	          if (value > 0) input.value = value - 1;
	        });

	        plusBtn.addEventListener('click', () => {
	          let value = parseInt(input.value, 10);
	          if (value < 20) input.value = value + 1;
	        });
	      });
	    });

	    let hour;
	    let minute;

	    window.addEventListener('load', async () => {
	      try {
	        const res = await fetch('/bentou/deadline', { method: 'POST' });
	        if (!res.ok) throw new Error('サーバーエラー');
	        const data = await res.json();
	        if (data.status === 'ok') {
	          hour = data.hour;
	          minute = data.minute;
	          setInterval(updateRemainingTime, 1000);
	        } else {
	          console.log('失敗', data.status);
	        }
	      } catch (err) {
	        console.error('失敗', err);
	      }
	    });

	    function updateRemainingTime() {
	      const now = new Date();
	      const target = new Date();
	      target.setHours(hour, minute, 0, 0);
	      let diffMs = target - now;

<!--	      if (diffMs < 0) {-->
<!--	        document.getElementById("deadline").textContent = '締め切りが過ぎました！';-->
<!--	        document.getElementById("order-button").disabled = true;-->
<!--	        return;-->
<!--	      } else {-->
<!--	        const diffHours = Math.floor(diffMs / (1000 * 60 * 60));-->
<!--	        const diffMinutes = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60));-->
<!--	        const diffSeconds = Math.floor((diffMs % (1000 * 60)) / 1000);-->
<!--	        document.getElementById("deadline").textContent =-->
<!--	          '締め切りまで' + diffHours + ':' +-->
<!--	          diffMinutes.toString().padStart(2, '0') + ':' +-->
<!--	          diffSeconds.toString().padStart(2, '0');-->
<!--	      }-->
	    }
	  </script>
	  <!-- 共通フッター -->
	<%@ include file="/common/footer.jsp"%>

</body>
</html>