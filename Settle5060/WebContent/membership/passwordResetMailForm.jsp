<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
</head>
<body>
    <div class="login-box">
    <h1 class="login-title">パスワード変更</h1>
    　　<form action="PasswordChangeMail" method="post" >
			<p class="form-comment">メールアドレスを入力してください。</p>
	        <div class="login-input">
	            <label for="user-id" class="infochange-input-element">ID(email)</label>
	            <input name="newMail" type="email" id="email_1" placeholder="✉Mail" class="login-input-element" required>
	        </div>
	            <button type="submit" class="form-change-btn">変更</button>
	        </div>
　　　　</form>
	</div>
</body>
</html>
