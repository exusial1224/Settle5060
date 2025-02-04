<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic2.css">
</head>
<body>
    <div class="container">
        <h1 class="title-text">メールアドレス変更</h1>
        <hr>
        <form id="EmailChangeForm" action="EmailChange" method="post" class="email-change-form">
            <p class="text-form"><label for="newMail">新しいメールアドレス</label></p>
            <input type="email" id="newMail" name="newMail" class="input-form" placeholder="✉Mail" required>

            <input type="submit" value="変更" class="form-change-btn">
        </form>
    </div>
</body>
</html>
