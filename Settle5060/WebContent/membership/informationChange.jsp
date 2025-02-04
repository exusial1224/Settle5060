<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/generic.css">
    <title>会員情報変更</title>

</head>
<body>
    <div class="container">
        <h1 class="title-text">会員情報変更</h1>
        <hr>
        <div class="error-text">${birthError}</div>
        <form id="memberChangeForm" action="InformationChangeConfirm" method="post">
        <div class="login-input">
            <label for="name" class="text-form">氏名</label>
            <input type="text" id="name" name="name" value="${param.name}"  class="input-form" required>
		</div>

		<div class="login-input">
            <label for="tel" class="text-form">電話番号</label>
            <input type="tel" id="tel" name="tel" value="${param.tel}"  class="input-form" required>
		</div>

		<div class="login-input">
            <label for="address" class="text-form">住所</label>
            <input type="text" id="address" name="address" value="${param.address}" class="input-form" required>
		</div>

		<div class="login-input">
            <label for="birth" class="text-form">生年月日</label>
            <input type="text" id="birth" name="birth" value="${param.birth}"  class="input-form" required>
		</div>

		<div class="link">
			<a href="EmailChangeDisplay" class="btn">メールアドレス変更</a>
	        <a href="PasswordChangeDisplay" class="btn">パスワード変更</a>
		</div>

            <input type="submit" value="確認" class="submit-button">
        </form>
    </div>
</body>
</html>