<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.time.YearMonth"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>過去の注文履歴</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header_admin.css">


<style> 
/* 表で表示する */

.order-table {
	width: 80%;
	border-collapse: collapse;
	margin-top: 20px;
}

.order-table th, .order-table td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: left;
}

.order-table th {
	background-color: #ffb84d;
	color: black;
}

.header-content {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.sort-area {
	display: flex;
	align-items: center;
}
</style>
</head>

<body>
	<!-- 共通ヘッダー呼び出し -->
	<%@ include file="/common/header.jsp"%>

	<div class="container">
		<h1>過去の注文履歴</h1>
		<div class="header-content">
			<form action="UserOrderListServlet" method="post" class="sort-area">
				ソート（月） <select name="month" onchange="this.form.submit()">
					<%
					YearMonth now = YearMonth.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月");
					DateTimeFormatter valueFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

					/* ソートの選択可能月を3カ月前までに設定 */
					for (int i = 0; i < 3; i++) {
						YearMonth ym = now.minusMonths(i);
						String value = ym.format(valueFormatter);
						String text = ym.format(formatter);
						

						pageContext.setAttribute("optionValue", value); 
					%>
					<option value="<%= value %>"
						<c:if test="${selectedMonth == optionValue }">selected</c:if>>
						<%=text%>
					</option>
					<%
					}
					pageContext.removeAttribute("optionValue");
					%>
				</select>
			</form>
		</div>

		<table class="order-table">
			<tr>
				<th>日付</th>
				<th>名前</th>
				<th>個数</th>
				<th>金額</th>
			</tr>

			<c:forEach var="item" items="${orderHistoryList}">
				<tr>
					<td><c:set var="formattedDate">
							<%-- Javaスクリプトレットを使用してLocalDateTimeを日付のみでフォーマット --%>
							<%=((model.EmployeeOrder) pageContext.getAttribute("item")).getDateTime()
		.format(java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd"))%>
						</c:set> ${formattedDate}</td>
					<td>${item.itemName}</td>
					<td>${item.totalQuantity}</td>
					<td>${item.totalPrice}円</td>
				</tr>
			</c:forEach>

			<c:if test="${empty orderHistoryList}">
				<tr>
					<td colspan="4">選択された月には注文履歴がありません。</td>
				</tr>
			</c:if>
		</table>
	</div>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>
