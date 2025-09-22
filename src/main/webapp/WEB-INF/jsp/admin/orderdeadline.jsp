<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文締め切り時間の変更</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>
<%@ include file="/common/header_admin.jsp" %>
    <h1>注文締め切り時間の変更</h1>

    <div>
        ● 現在の注文締め切り時間：<strong>${deadlineTime}</strong>
    </div>

    <form action="${pageContext.request.contextPath}/OrderDeadlineServlet" method="post">
        <div>
            ● 変更後の時間：
            <select name="newTime">
                <option value="09:30">9:30</option>
                <option value="09:40">9:40</option>
                <option value="09:50">9:50</option>
                <option value="10:00">10:00</option>
                <option value="10:10">10:10</option>
                <option value="10:20">10:20</option>
                <option value="10:30">10:30</option>
            </select>
        </div>
        <button type="submit">変更する</button>
    </form>

    <%@ include file="/common/footer.jsp" %>
</body>
</html>
