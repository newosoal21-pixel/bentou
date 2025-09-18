<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文画面サンプル</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
<!--  CSSは別ファイルにしてください -->

</head>
<body>

<!-- 共通ヘッダー呼び出し -->
    <%@ include file="/css/header.jsp" %>
    
<main>
  <!-- タイトルと日付 -->
  <div class="form-header">
    <h1>注文フォーム</h1>
    <div class="order-date">0000/00/00</div>
  </div>

  <!-- 締め切りまでの時間 -->
  <p id="deadline" class="deadline">締め切りまであと00:00:00！！</p>

  <form method="post" action="#">
    <div class="product-list">

      <!-- 商品カード例 -->
      <div class="product">
        <img src="${pageContext.request.contextPath}/images/bentou1.png" alt="しゃけ弁当">
        <div class="product-name">しゃけ弁当</div>
        <div class="product-info">¥600円（600kcal）</div>
        <div class="quantity-control">
          <button type="button" class="minus">−</button>
          <input type="number" value="0" min="0" max="20" name="syake">
          <button type="button" class="plus">＋</button>
        </div>
      </div>

      <div class="product">
        <img src="${pageContext.request.contextPath}/images/bentou2.png" alt="からあげ弁当">
        <div class="product-name">からあげ弁当</div>
        <div class="product-info">¥600円（600kcal）</div>
        <div class="quantity-control">
          <button type="button" class="minus">−</button>
          <input type="number" value="0" min="0" max="20" name="ebi">
          <button type="button" class="plus">＋</button>
        </div>
      </div>

      <div class="product">
        <img src="${pageContext.request.contextPath}/images/bentou３.png" alt="幕ノ内弁当">
        <div class="product-name">幕ノ内弁当</div>
        <div class="product-info">¥600円（600kcal）</div>
        <div class="quantity-control">
          <button type="button" class="minus">−</button>
          <input type="number" value="0" min="0" max="20" name="karaage">
          <button type="button" class="plus">＋</button>
        </div>
      </div>

      <div class="product">
        <img src="images/makunouchi.png" alt="幕ノ内弁当">
        <div class="product-name">幕ノ内弁当</div>
        <div class="product-info">¥700円（700kcal）</div>
        <div class="quantity-control">
          <button type="button" class="minus">−</button>
          <input type="number" value="0" min="0" max="20" name="makunouchi">
          <button type="button" class="plus">＋</button>
        </div>
      </div>

      <div class="product">
        <img src="images/potato.png" alt="ポテトフライ">
        <div class="product-name">ポテトフライ</div>
        <div class="product-info">¥300円（500kcal）</div>
        <div class="quantity-control">
          <button type="button" class="minus">−</button>
          <input type="number" value="0" min="0" max="20" name="potato">
          <button type="button" class="plus">＋</button>
        </div>
      </div>

      <div class="product">
        <img src="images/tea.png" alt="お茶">
        <div class="product-name">お茶</div>
        <div class="product-info">¥150円</div>
        <div class="quantity-control">
          <button type="button" class="minus">−</button>
          <input type="number" value="0" min="0" max="20" name="tea">
          <button type="button" class="plus">＋</button>
        </div>
      </div>

    </div>

    <!-- 戻る・注文ボタン -->
    <div class="button-area">
      <button type="button" class="action-btn">戻る</button>
      <button type="submit" class="action-btn" id="order-button">注文する</button>
    </div>
  </form>
</main>
<!--  JavaScriptは別ファイルにしてください -->
	<script>
 //数量の増減ボタン
  document.querySelectorAll('.product').forEach(product => {
 	const minusBtn = product.querySelector('.minus');
 	const plusBtn = product.querySelector('.plus');
 	const input = product.querySelector('input');

 	minusBtn.addEventListener('click', () => {
 	 let value = parseInt(input.value, 10);
 	 //1未満にはならないようにする
 	 if (value > 0) {
 	  input.value = value - 1;
 	 }
 	});

 	plusBtn.addEventListener('click', () => {
 	 let value = parseInt(input.value, 10);
	 //21以上にはならないようにする
 	 	 if (value < 20) {
 	 	input.value = value + 1;
 	 }
 	});
  });
//締め切り時間までのカウントダウン
	let hour;	//締め切り時間（時）
 	let minute;	//締め切り時間（分）
 	//画面を読み込んだらスタートする
	window.addEventListener('load', async () => {
	try {
	  const res = await fetch('/favorite/deadline',{method: 'POST'});
	  if (!res.ok) throw new Error('サーバーエラー');
	  const data = await res.json();
	  if(data.status=='ok'){
		  //締め切り時間（時、分）はJSON形式で受け取った値を使用する
	 	 	hour = data.hour;
	 	 	minute = data.minute;
	 	 	// 1秒ごとに更新
	 	 	setInterval(updateRemainingTime, 1000);
	 	}else{
	 	 console.log('失敗', data.status);
	 		
	 	}
	 } catch (err) {
	  console.error('失敗', err);

	 }
	});
 function updateRemainingTime() {
 	 const now = new Date();
 	 //締め切りの時刻を作成
 	 const target = new Date();
 	 target.setHours(hour, minute, 0, 0); // 時、分、秒、ミリ秒を設定
 	 // 差分ミリ秒
 	 let diffMs = target - now;
 	 // もしすでに締め切り時間を過ぎていたら、メッセージを表示、ボタンを押せなくする
 	 if (diffMs < 0) {
 	 	document.getElementById("deadline").textContent ='締め切りが過ぎました！';
 	 	document.getElementById("order-button").disabled=true;
 	 	return;
 	 }else{
 	 	// ミリ秒を時・分・秒に変換
 	 	const diffHours = Math.floor(diffMs / (1000 * 60 * 60));
 	 	const diffMinutes = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60));
 	 	const diffSeconds = Math.floor((diffMs % (1000 * 60)) / 1000);
 	 	//残り時間の分と秒は二桁表示にする
 	 		document.getElementById("deadline").textContent ='締め切りまで' + diffHours+':'
 	 			+ diffMinutes.toString().padStart( 2, '0') + ':' + diffSeconds.toString().padStart( 2, '0');
 	 }
 	}

 </script>
 
 <!-- フッター -->
  <%@ include file="/css/footer.jsp" %>
  
</body>
</html>
