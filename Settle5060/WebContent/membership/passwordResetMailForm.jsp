<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">
</head>
<body>
    <div class="container">
    	<h1 class="title-text">パスワード変更</h1>
    	<hr>
    　　<form action="PasswordChangeMail" method="post" >
			<p class="send-mail-info" style="text-align: center;">メールアドレスを入力してください。</p>
	        <div class="login-input">
	            <label for="user-id" class="text-form">ID(email)</label>
	            <input name="newMail" type="email" id="email_1" placeholder="✉Mail" class="input-form" required>
	        </div>
	            <input type="submit" value="送信" class="submit-button">
	        </div>
　　　　</form>
	</div>
</body>
</html>
