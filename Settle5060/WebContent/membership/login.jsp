<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>

    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="../css/generic.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
	<div class="outer" onclick="toggleRainbow()">
        <div class="iconGear"><div></div></div>
    </div>

	<div class="container">
		<!--  <h1 class="login-title">-->
		<p class=title-text> SETTLEにログイン</p>
	    <hr>
	    <form action="Login" method="post" class="login-form">

	    	<p class="text-form"><label for="mail" class="login-label">メールアドレス：</label></p>
	    	<input name="mail" type="email" id="user-id" placeholder="✉Mail" class="input-form" required>

	    	<p class="text-form"><label for="password" class="login-label">パスワード：</label></p>
	    	<input name="password" type="password" id="password" placeholder="🔒Password" class="input-form" required>

			<c:if test ="${loginError != null}">
  		   		<div class="error-text">パスワードまたはメールアドレスが無効です。</div>
			</c:if>


	        <a href="PasswordResetMailFormDisplay" class="pass_link">▶パスワードを忘れた場合はこちら</a>
	        <p class="login-btn"><input type="submit" value="ログイン" class="login-input-btn"></p>
	        <a href="AddNewMemberDisplay" class="new_user_url">新規会員登録</a>

	    </form>
	</div>
	<script>
	    var loginError = "${loginError}";
	    if (loginError) {
	        var elements = document.querySelectorAll('input');
	        elements.forEach(function(element) {
	            element.classList.add('input-error');
	        });
	    }

       	function toggleRainbow() {
            let title = document.querySelector('.title-text');
            title.classList.toggle('rainbow');
        }
	</script>

</body>
</html>
