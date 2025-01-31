<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">
	<%@include file="header.jsp"%>
</head>
<body>
    <div class="container">
    <h1 class="title-text">パスワード変更</h1>
    <hr>
    　　<form action="PasswordChange" method="post">
    	<div class="login-input" >
	            <label for="oldPassword" class="text-form">旧パスワード</label>
	            <input name="password" type="password" id="oldPassword" placeholder="🔒Password" class="input-form" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>
	            </div>
	        <div class="login-input" >
	            <label for="password" class="text-form">新パスワード</label>
	            <input name="newPassword" type="password" id="password" placeholder="🔒Password" class="input-form" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>
	            </div>
	            <div class="login-input">
	            <label for="confirm_password" class="text-form">新パスワード確認</label>
	            <input name="confirmPassword" type="password" id="confirmPassword" placeholder="🔒Password" class="input-form" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>
	           <input type="submit" value="変更" class="submit-button">
	        </div>
	    </form>
	    <button id="showPasswordButton">表示</button>
	</div>
</body>
<script type="text/javascript" src="../js/password_option.js"></script>
</html>
