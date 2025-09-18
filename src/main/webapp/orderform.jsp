<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文画面サンプル</title>
<!--  CSSは別ファイルにしてください -->
<style>
body {
	font-family: Arial, sans-serif;
}

.product-list {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
	gap: 20px;
}

.product {
	background: #fff;
	text-align: center;
	border: 1px solid #ddd;
	border-radius: 10px;
	padding: 15px;
}

.product img {
	width: 100%;
	border-radius: 8px;
}

.product-name {
	margin: 10px 0;
	font-weight: bold;
}

.quantity-control {
	display: flex;
	align-items: center;
	justify-content: center;
	margin-top: 10px;
}

.quantity-control button {
	width: 30px;
	height: 30px;
	font-size: 18px;
	border: none;
	background-color: #999;
	color: white;
	border-radius: 5px;
	cursor: pointer;
}

.quantity-control input {
	width: 50px;
	text-align: center;
	font-size: 16px;
	margin: 0 5px;
}

#deadline {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<header>
		<h1>注文画面</h1>
	</header>
	<main>
		<!-- 締め切りまでの時間を表示する -->
		<p id="deadline">締め切りまで0:00:00</p>
		<form method="post" action="#">
			<div class="product-list">
				<div class="product">
					<img src="images/bento.png" alt="商品A">
					<div class="product-name">商品A</div>
					<div class="quantity-control">
						<button type="button" class="minus">−</button>
						<input type="number" value="0" min="0" max="20" name="1">
						<button type="button" class="plus">＋</button>
					</div>
				</div>

				<div class="product">
					<img src="images/bento.png" alt="商品B">
					<div class="product-name">商品B</div>
					<div class="quantity-control">
						<button type="button" class="minus">−</button>
						<input type="number" value="0" min="0" max="20" name="2">
						<button type="button" class="plus">＋</button>
					</div>
				</div>

				<div class="product">
					<img src="images/bento.png" alt="商品C">
					<div class="product-name">商品C</div>
					<div class="quantity-control">
						<button type="button" class="minus">−</button>
						<input type="number" value="0" min="0" max="20" name="3">
						<button type="button" class="plus">＋</button>
					</div>
				</div>
			</div>
			<p>
				<input type="submit" value="注文" id="order-button">
			</p>
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
	  const res = await fetch('/bentou/deadline',{method: 'POST'});
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
</body>
</html>
