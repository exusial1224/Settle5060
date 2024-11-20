<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/generic.css">
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>新規会員登録</title>

</head>
<body>
	<div class="container">
	  <p class="gaming">新規会員登録</p>
	  <hr>
	  <form id="AddNewMemberForm" action="AddNewMemberConfirm" method="post" class="signup-form">
	    <p class="signup-label"><label for="name">氏名</label></p>
	    <input type="text" id="name" name="name" class="custom-input" required>

	    <p class="signup-label"><label for="mail">メールアドレス</label></p>
	    <input type="email" id="mail" name="mail" class="custom-input" required>

	    <p class="signup-label"><label for="tel">電話番号</label></p>
	    <input type="tel" id="tel" name="tel" pattern="\d{10}|\d{11}" class="custom-input" required>

	    <p class="signup-label"><label for="address">住所</label></p>
	    <input type="text" id="address" name="address" class="custom-input" required>

	    <p class="signup-label"><label for="birth">生年月日(8桁)</label></p>
	    <input type="tel" id="birth" name="birth" class="custom-input" required>

	    <p class="signup-label"><label for="password">パスワード</label></p>
	    <input type="password" id="password" name="password" class="passwordInput" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>

	    <p class="signup-label"><label for="confirmPassword">パスワード確認</label></p>
	    <input type="password" id="confirmPassword" name="confirmPassword" class="passwordInput" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>


	    <input type="submit" value="確認" class="submit-button">
	  </form>
	  <button id="showPasswordButton">表示</button>
	</div>
</body>
<script type="text/javascript" src="../js/password_option.js"></script>
</html>
