<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>注文画面サンプル</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
</head>
<body>

  <!-- 共通ヘッダー -->
  <%@ include file="/common/header.jsp" %>

  <main>
    <!-- タイトルと日付 -->
    <div class="form-header">
      <h1>注文フォーム</h1>
      <div class="order-date">0000/00/00</div>
    </div>

    <!-- 締め切りまでの時間 -->
    <p id="deadline" class="deadline">締め切りまであと00:00:00！！</p>

    <form method="post" action="OrderFormServlet">
      <div class="product-list">
        <c:forEach var="product" items="${productList}">
          <div class="product">
            <img src="${pageContext.request.contextPath}/${product.imagePath}" alt="${product.name}">
            <div class="product-name">${product.name}</div>
            <div class="product-info">${product.price}円（${product.cal}kcal）</div>
            <div class="quantity-control">
              <button type="button" class="minus">−</button>
              <input type="number" value="0" min="0" max="20" name="${product.id}">
              <button type="button" class="plus">＋</button>
            </div>
          </div>
        </c:forEach>
      </div>

      <!-- 戻る・注文ボタン -->
      <div class="button-area">
        <button type="button" class="action-btn">戻る</button>
        <button type="submit" class="action-btn" id="order-button">注文する</button>
      </div>
    </form>
  </main>

  <!-- JavaScript（数量増減・締め切りカウントダウン） -->
  <script>
    document.querySelectorAll('.product').forEach(product => {
      const minusBtn = product.querySelector('.minus');
      const plusBtn = product.querySelector('.plus');
      const input = product.querySelector('input');

      minusBtn.addEventListener('click', () => {
        let value = parseInt(input.value, 10);
        if (value > 0) {
          input.value = value - 1;
        }
      });

      plusBtn.addEventListener('click', () => {
        let value = parseInt(input.value, 10);
        if (value < 20) {
          input.value = value + 1;
        }
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

      if (diffMs < 0) {
        document.getElementById("deadline").textContent = '締め切りが過ぎました！';
        document.getElementById("order-button").disabled = true;
        return;
      } else {
        const diffHours = Math.floor(diffMs / (1000 * 60 * 60));
        const diffMinutes = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60));
        const diffSeconds = Math.floor((diffMs % (1000 * 60)) / 1000);
        document.getElementById("deadline").textContent =
          '締め切りまで' + diffHours + ':' +
          diffMinutes.toString().padStart(2, '0') + ':' +
          diffSeconds.toString().padStart(2, '0');
      }
    }
  </script>

  <!-- 共通フッター -->
  <%@ include file="/common/footer.jsp" %>

</body>
</html>