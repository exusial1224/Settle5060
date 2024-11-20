
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="../css/generic.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>

<div class="login-box">
    <!--  <h1 class="login-title">-->
    <p class=gaming> SETTLEにログイン</p>
    <!-- </h1> -->
    <hr>
    <form action="Login" method="post" class="login-form">
        <div class="login-input">
            <label for="mail" class="login-label">メールアドレス：</label><br>
            <input name="mail" type="email" id="user-id" placeholder="✉Mail" class="login-input-element" required>
        </div>
        <div class="login-input">
            <label for="password" class="login-label">パスワード：</label><br>
            <input name="password" type="password" id="password" placeholder="🔒Password" class="login-input-element" required>
        </div>
        <a href="PasswordResetMailFormDisplay" class="pass_link">▶パスワードを忘れた場合はこちら</a>
        <p class="login-btn"><input type="submit" value="ログイン" class="login-input-btn"></p>
        <a href="AddNewMemberDisplay" class="new_user_url">新規会員登録</a>

    </form>
</div>

</body>
</html>
