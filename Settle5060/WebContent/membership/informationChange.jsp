<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>会員情報変更</title>

</head>
<body>
    <div class="container">
        <h1 class="login-title">会員情報変更</h1>
        <form id="memberChangeForm" action="InformationChangeConfirm" method="post">
        <div class="login-input">
            <label for="name" class="infochange-input-element">氏名</label>
            <input type="text" id="name" name="name" value="${param.name}"  class="login-input-element" required>
		</div>

		<div class="login-input">
            <label for="tel" class="infochange-input-element">電話番号</label>
            <input type="tel" id="tel" name="tel" value="${param.tel}"  class="login-input-element" required>
		</div>

		<div class="login-input">
            <label for="address" class="infochange-input-element">住所</label>
            <input type="text" id="address" name="address" value="${param.address}" class="login-input-element" required>
		</div>

		<div class="login-input">
            <label for="birth" class="infochange-input-element">生年月日</label>
            <input type="text" id="birth" name="birth" value="${param.birth}"  class="login-input-element" required>
		</div>

		<a href="EmailChangeDisplay" class="btn">メールアドレス変更</a>
        <a href="PasswordChangeDisplay" class="btn">パスワード変更</a>

            <input type="submit" value="確認" class="submit-button">
        </form>
    </div>
</body>
</html>