
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="../css/facilitylogin.css">
</head>
<body>

<div class="container">
    <!--  <h1 class="login-title">-->
    <p class=title-text>ログイン</p>
    <hr>
    <form action="facilityLogin" method="post" class="login-form">

            <div class="loginform-group"><label for="mail" class="login-label">メールアドレス</label></div>
            <input name="mail" type="email" id="user-id" placeholder="Mail" class="input-form" required>

            <p class="text-form"><label for="password" class="login-label">パスワード：</label></p>
            <input name="password" type="password" id="password" placeholder="Password" class="input-form" required>

        <a href="PasswordResetMailFormDisplay" class="pass_link">▶新規登録またはパスワードを忘れた場合はこちら</a>
        <p class="login-btn"><input type="submit" value="ログイン" class="login-input-btn"></p>
    </form>
</div>

</body>
</html>
