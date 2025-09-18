<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ようこそ画面</title>
    <style>
        body {
            font-family: "メイリオ", sans-serif;
            margin: 0;
            background-color: #fff;
        }
        header {
            background-color: #fce58b;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 8px 16px;
        }
        .btn-back {
            padding: 6px 14px;
            border: 2px solid #000;
            border-radius: 6px;
            background: #fff;
            font-weight: bold;
            cursor: pointer;
        }
        .user-info {
            font-size: 14px;
            text-align: right;
        }
        .user-info img {
            width: 30px;
            vertical-align: middle;
            margin-left: 6px;
        }
        .main {
            text-align: center;
            padding: 60px 20px;
        }
        .main img {
            width: 90px;
            margin-bottom: 20px;
        }
        .welcome {
            font-size: 32px;
            color: #804000;
            margin: 10px 0;
        }
        .button-area {
            margin-top: 30px;
        }
        .action-btn {
            display: inline-block;
            margin: 0 40px;
            padding: 10px 25px;
            border: 2px solid #ffcc00;
            border-radius: 6px;
            color: #ff9900;
            font-weight: bold;
            text-decoration: none;
            background: #fff;
            cursor: pointer;
        }
        footer {
            background: #fce58b;
            text-align: center;
            padding: 5px;
            font-size: 12px;
            margin-top: 40px;
        }
    </style>
</head>
<body>
<header>
    <!-- マイページ戻るボタン -->
    <form action="mypage.jsp" method="get">
        <button type="submit" class="btn-back">マイページへ戻る</button>
    </form>
    
    <!-- ユーザー情報 -->
    <div class="user-info">
        社員番号 ${employee.employeesId}<br>
        部署名 ${employee.departmentName}　名前 ${employee.userName}
        <img src="images/rice_icon.png" alt="icon">
    </div>
</header>

<div class="main">
    <!-- アイコン -->
    <img src="images/rice_set.png" alt="ごはんアイコン">
    
    <!-- ようこそメッセージ -->
    <div class="welcome">ようこそ！<br>${employee.userName}さん</div>
    
    <!-- アクションボタン -->
    <div class="button-area">
        <form action="LogoutServlet" method="post" style="display:inline;">
            <button type="submit" class="action-btn">ログアウト</button>
        </form>
        <form action="UserMypageServlet" method="get" style="display:inline;">
            <button type="submit" class="action-btn">注文へ</button>
        </form>
    </div>
</div>

<footer>
    ごはん係DX
</footer>
</body>
</html>
