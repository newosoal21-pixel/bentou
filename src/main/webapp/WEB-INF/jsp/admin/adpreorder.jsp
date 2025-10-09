<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> ← 不要なので削除 --%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<title>発注画面</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<%@ include file="/common/header_admin.jsp" %>
	<main>
		<section class="left">
			<h1>発注画面</h1>
			<div class="date-total">${orderDate} 合計金額 ${totalAmount}円</div>
			<table>
				<thead>
					<tr>
						<th>名前</th>
						<th>個数</th>
						<th>金額</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderList}">
						<tr>
							<td>${order.itemName}</td>
							<td>${order.totalQuantity != null ? order.totalQuantity : ''}</td>
							<td>${order.totalPrice != null ? order.totalPrice : ''}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
		<section class="right">
			<button class="copyBtn" onclick="copyText()">コピー</button>
			<pre id="orderText">
【お弁当発注メール ひな形】
件名：お弁当発注のご連絡
あわじや弁当　ご担当者様
いつもお世話になっております。
株式会社はっとりの服部です。
下記の通り、お弁当の発注をさせていただきます。
ご確認のほど、よろしくお願いいたします。
■ 発注日：${orderDate}  <%-- fmtタグの代わりにELをそのまま使用 --%>
■ 配達時間：12時頃
■ 注文内容：
<c:forEach var="item" items="${orderList}">
  ${item.itemName} ${item.totalQuantity}個 ${item.totalPrice}円
</c:forEach>
■ 合計数量：${totalQuantity}個
ご不明点などございましたらご連絡ください。
どうぞよろしくお願いいたします。
——————————————
株式会社はっとり
服部 平時（名前）
メール：xxxx@xxxx.com
電話：000-0000-0000
</pre>
<form action="${pageContext.request.contextPath}/AdminOrderServlet" method="post">
    <button type="submit" class=".button" <c:if test="${orderCompleted}">disabled="disabled"</c:if>>
        <c:choose>
            <c:when test="${orderCompleted}">本日は発注済み</c:when>
            <c:otherwise>発注する</c:otherwise>
        </c:choose>
    </button>
</form>  <%-- ここで正しく閉じます --%>
		</section>
	</main>
<%@ include file="/common/footer.jsp" %>

	<script>
  function copyText() {
    const orderText = document.getElementById('orderText').innerText;
    navigator.clipboard.writeText(orderText)
      .then(() => alert('発注メール文をコピーしました'))
      .catch(() => alert('コピーに失敗しました'));
  }
</script>
</body>
</html>