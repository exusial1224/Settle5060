<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>新規会員登録</title>
	<script>
	  function validatePassword() {
	    var password = document.getElementById('password');
	    var confirmPassword = document.getElementById('confirmPassword');

	    if (password.value !== confirmPassword.value) {
	      confirmPassword.setCustomValidity('パスワードが一致しません。');
	    } else {
	      confirmPassword.setCustomValidity('');
	    }
	  }

	  window.onload = function() {
	    document.getElementById('password').onchange = validatePassword;
	    document.getElementById('confirmPassword').onkeyup = validatePassword;
	  }
	</script>

</head>
<body>
	<div class="container">
	  <h1>新規会員登録</h1>
	  <form id="AddNewMemberForm" action="AddNewMemberConfirm" method="post" class="signup-form">
	    <p class="signup-label"><label for="name">氏名</label></p>
	    <input type="text" id="name" name="name" class="custom-input" required>

	    <p class="signup-label"><label for="mail">メールアドレス</label></p>
	    <input type="mail" id="mail" name="mail" class="custom-input" required>

	    <p class="signup-label"><label for="tel">電話番号</label></p>
	    <input type="tel" id="tel" name="tel" pattern="\d{10}|\d{11}" class="custom-input" required>

	    <p class="signup-label"><label for="address">住所</label></p>
	    <input type="address" id="address" name="address" class="custom-input" required>

	    <p class="signup-label"><label for="birth">生年月日(8桁)</label></p>
	    <input type="birth" id="birth" name="birth" class="custom-input" required>

	    <p class="signup-label"><label for="password">パスワード</label></p>
	    <input type="password" id="password" name="password" class="custom-input" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>

	    <p class="signup-label"><label for="confirmPassword">パスワード確認</label></p>
	    <input type="password" id="confirmPassword" name="confirmPassword" class="custom-input" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>

	    <input type="submit" value="確認" class="submit-button">
	  </form>
	</div>
</body>
</html>
