<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
  <button class="button" onclick="location.href='${pageContext.request.contextPath}/AdminMainServlet'">管理者用画面に戻る</button>
  <div class="user-info-container">
    <div class="user-info">
      管理者用<br>
      社員ID ${sessionScope.employeeId} <br>
      名前 ${sessionScope.employeeName}
    </div>
    <a href="adminbase.jsp">
      <img src="${pageContext.request.contextPath}/images/rice.png" alt="ごはんイラスト" />
    </a>
  </div>
</header>
