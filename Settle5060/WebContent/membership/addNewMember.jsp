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
	  <p class="title-text">新規会員登録</p>
	  <hr>
	  <!-- 2/5 エラー追加 -->>
	  <div class="error-text">${mail}${error}</div>
	  <form id="AddNewMemberForm" action="AddNewMemberConfirm" method="post" class="signup-form">
	    <p class="text-form"><label for="name">氏名</label></p>
	    <input type="text" id="name" name="name" class="input-form" required>

	    <p class="text-form"><label for="mail">メールアドレス</label></p>
	    <input type="email" id="mail" name="mail" class="input-form" required>

	    <p class="text-form"><label for="tel">電話番号</label></p>
	    <input type="tel" id="tel" name="tel" pattern="\d{10}|\d{11}" class="input-form" placeholder="○○桁" required>

	    <p class="text-form"><label for="address">住所</label></p>
	    <input type="text" id="address" name="address" class="input-form" placeholder="例：○○県○○市○○町○-○-○" required>

	    <p class="text-form"><label for="birth">生年月日(8桁)</label></p>
	    <input type="tel" id="birth" name="birth" class="input-form" placeholder="例：1998年12月5日 → 19981205" required>

	    <p class="text-form"><label for="password">パスワード</label></p>
	    <input type="password" id="password" name="password" class="passwordInput" pattern="[!-~]{8,}" title="8文字以上で入力してください。" placeholder="大小英数字を全て含めてください" required>

	    <p class="text-form"><label for="confirmPassword">パスワード確認</label></p>
	    <input type="password" id="confirmPassword" name="confirmPassword" class="passwordInput" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>


	    <input type="submit" value="確認" class="submit-button">
	  </form>
	  <button id="showPasswordButton">表示</button>
	</div>
</body>
<script type="text/javascript" src="../js/password_option.js"></script>
</html>
