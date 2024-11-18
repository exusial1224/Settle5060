<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>

</head>
<body>
    <div class="login-box">
        <h1 class="login-title">メールアドレス変更</h1>
        <form id="EmailChangeForm" action="EmailChange" method="post" class="email-change-form">
            <p class="email-change-label"><label for="newMail">新しいメールアドレス</label></p>
            <input type="email" id="newMail" name="newMail" class="custom-input" placeholder="✉Mail" required>

            <input type="submit" value="変更" class="form-change-btn">
        </form>
    </div>
</body>
</html>
