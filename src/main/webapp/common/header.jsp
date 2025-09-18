   <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
  <!-- 左側：マイページ戻るボタン -->
  <button class="action-btn" onclick="location.href='${pageContext.request.contextPath}/UserMypageServlet'">
    マイページへ戻る
  </button>
  
 
  
  <div class="user-info-container">
    <div class="header-right">
		社員番号 ${sessionScope.employeeNo}<br>
		部署名 ${sessionScope.department}<br>
		名前 ${sessionScope.employeeName}
    </div>	
    <a href="WelcomeServlet">
      <img src="${pageContext.request.contextPath}/images/rice.png" alt="ごはんイラスト" />
    </a>
  </div>
</header>
