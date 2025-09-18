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

    <section class="order-summary">
      <p>&lt;総合&gt; 0000/00/00(本日) 合計金額00000円</p>
      <table>
        <thead>
          <tr><th>名前</th><th>個数</th><th>金額</th></tr>
        </thead>
        <tbody>
          <tr><td>しゃけ弁当</td><td>20</td><td>12000円</td></tr>
          <tr><td>幕ノ内弁当</td><td>15</td><td>10500円</td></tr>
          <tr><td>ポテト</td><td></td><td></td></tr>
          <tr><td>・・・・・・</td><td>・・・・・</td><td>・・・・</td></tr>
        </tbody>
      </table>
    </section>

    <section class="order-summary">
      <p>&lt;部署別&gt; 開発部 0000/00/00(本日) 合計金額00000円</p>
      <table>
        <thead>
          <tr><th>名前</th><th>個数</th><th>金額</th></tr>
        </thead>
        <tbody>
          <tr><td>しゃけ弁当</td><td>20</td><td>12000円</td></tr>
          <tr><td>幕ノ内弁当</td><td>15</td><td>10500円</td></tr>
          <tr><td>ポテト</td><td></td><td></td></tr>
          <tr><td>・・・・・・</td><td>・・・・・</td><td>・・・・</td></tr>
        </tbody>
      </table>
    </section>

    <div class="actions">
      <button>印刷</button>
      <button>CSV DL</button>
    </div>
  </main>
  <%@ include file="/common/footer.jsp" %>

</body>
</html>