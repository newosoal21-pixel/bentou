<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文締め切り時間の変更 - ごはん係DX</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"> 
</head>
<body>
<%@ include file="/common/header_admin.jsp" %>

    <main>
        <h1>注文締め切り時間の変更</h1>

        <div class="section">
            ● 現在の注文締め切り時間
            <div class="time">10:00</div>
        </div>

        <form action="OrderDeadlineServlet" method="post">
            <div class="section">
                ● 変更後の時間
                <select name="newTime">
                    <option value="10:00">9:30</option>
                    <option value="10:10">9:40</option>
                    <option value="10:20">9:50</option>
                    <option value="10:30">10:00</option>
                    <option value="10:10">10:10</option>
                    <option value="10:20">10:20</option>
                    <option value="10:30">10:30</option>
                </select>
            </div>
            <button type="submit" class="submit-button">変更する</button>
        </form>
    </main>

 <%@ include file="/common/footer.jsp" %>
</body>
</html>
