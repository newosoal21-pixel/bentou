<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ログイン | ごはん係DX</title>
  <style>
    body {
      margin: 0;
      font-family: 'Yu Gothic', 'Hiragino Kaku Gothic ProN', Meiryo, sans-serif;
      background-color: white;
    }

    /* ヘッダー */
    header {
      background-color: #FFE79B;
      height: 60px;
      display: flex;
      justify-content: flex-end;
      align-items: center;
      padding: 0 20px;
      font-size: 14px;
    }

    /* メイン */
    main {
      height: calc(100vh - 120px);
      display: flex;
      flex-direction: column;
      justify-content: center;
      padding: 0 10%;
    }

    h1 {
      font-size: 32px;
      color: #7a3e00;
      margin-bottom: 30px;
    }

    form {
      display: flex;
      flex-direction: column;
      gap: 20px;
    }

    .form-row {
      display: flex;
      align-items: center;
      gap: 20px;
    }

    label {
      width: 200px;
      font-size: 18px;
    }

    input[type="text"],
    input[type="password"] {
      flex: 1;
      padding: 10px;
      border: 2px solid #000;
      font-size: 16px;
    }

    .login-button {
      align-self: flex-end;
      margin-top: 30px;
      padding: 10px 30px;
      font-size: 16px;
      border: 2px solid #FFC107;
      color: #7a3e00;
      background-color: transparent;
      border-radius: 8px;
      cursor: pointer;
    }

    .login-button:hover {
      background-color: #FFE79B;
    }

    /* フッター */
    footer {
      background-color: #FFE79B;
      height: 60px;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 14px;
      color: #7A3E1D;
    }
  </style>
</head>
<body>

  <header>
    管理者用
  </header>

  <main>
    <h1>ログイン</h1>
    <form action="#" method="post">
      <div class="form-row">
        <label for="employee-id">・社員ID</label>
        <input type="text" id="employee-id" name="employee-id" required>
      </div>
      <div class="form-row">
        <label for="admin-password">・管理者用パスワード</label>
        <input type="password" id="admin-password" name="admin-password" required>
      </div>
      <button type="submit" class="login-button">ログイン</button>
    </form>
  </main>

  <footer>
    ごはん係DX
  </footer>

</body>
</html>