<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文締め切り時間の変更 - ごはん係DX</title>
    <style>
        body {
            margin: 0;
            font-family: "Hiragino Kaku Gothic ProN", Meiryo, sans-serif;
            background-color: #fff;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        header, footer {
            background-color: #FFE69A;
            padding: 10px 20px;
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .back-button {
            background-color: black;
            color: #FFE69A;
            padding: 6px 12px;
            border-radius: 5px;
            font-size: 14px;
            text-decoration: none;
        }
        .user-info {
            text-align: right;
            font-size: 14px;
        }
        main {
            flex: 1;
            padding: 40px;
        }
        h1 {
            font-size: 28px;
            margin-bottom: 40px;
        }
        .section {
            margin-bottom: 30px;
            font-size: 18px;
        }
        .time {
            font-size: 20px;
            margin-top: 10px;
            margin-left: 20px;
        }
        select {
            font-size: 16px;
            padding: 6px;
            margin-left: 20px;
        }
        .submit-button {
            margin-top: 30px;
            padding: 10px 30px;
            font-size: 16px;
            border: 2px solid black;
            background-color: white;
            cursor: pointer;
            border-radius: 8px;
        }
        .submit-button:hover {
            background-color: black;
            color: white;
        }
        footer {
            text-align: center;
            margin-top: auto;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <header>
        <a href="adminTop.jsp" class="back-button">管理者用画面に戻る</a>
        <div class="user-info">
            管理者用<br>
            社員ID 000-0000-0000<br>
            名前 ○○△△
        </div>
    </header>

    <main>
        <h1>注文締め切り時間の変更</h1>

        <div class="section">
            ● 現在の注文締め切り時間
            <div class="time">10:00</div>
        </div>

        <form action="changeDeadlineAction.jsp" method="post">
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

    <footer>
        ごはん係DX
    </footer>
</body>
</html>
