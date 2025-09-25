<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>発注完了画面</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

 <%@ include file="/common/header_admin.jsp" %>

  <main>
    <h1>発注完了</h1>

    <section class="order-summary" items="${orderList}">
      <p>&lt;総合&gt; ${orderDate} 合計金額 ${totalAmount}円</p>
      <table>
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

	<c:forEach var="list" items="${map}">
		<section class="order-summary" items="${orderList}">
	      <p>&lt;部署別&gt; ${entry.key} ${orderDate} 合計金額 ${totalPrice}円</p>
	      <table>
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
	 </c:forEach>
	 
    <div class="actions">
      <button>印刷</button>
      <button>CSV DL</button>
    </div>
  </main>
  <%@ include file="/common/footer.jsp" %>

</body>
</html>