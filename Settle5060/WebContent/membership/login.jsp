<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
</head>
<body>

<div class="login-box">
    <h1 class="login-title">SETTLEにログイン</h1>
    <form action="Login" method="post" class="login-form">
        <div class="login-input">
            <label for="user-id" class="login-label">メールアドレス</label>
            <input name="mail" type="email" id="user-id" placeholder="✉Mail" class="login-input-element" required>
        </div>
        <div class="login-input">
            <label for="password" class="login-label">パスワード</label>
            <input name="password" type="password" id="password" placeholder="🔒Password" class="login-input-element" required>
        </div>
        <p class="login-btn"><input type="submit" value="ログイン" class="login-input-btn"></p>
        <a href="AddNewMemberDisplay" class="btn">新規会員登録</a>
        <a href="PasswordResetMailFormDisplay" class="btn">パスワードを忘れた場合</a>
    </form>
</div>

</body>
</html>
