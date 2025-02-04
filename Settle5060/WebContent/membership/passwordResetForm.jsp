<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">
</head>

<body>
    <div class="container">
        <h1 class="title-text">パスワード変更</h1>
        <hr>

        <%
            //アドレスをURLから取得
            String newMail = request.getParameter("newMail");
        %>

        <form action="PasswordReset" method="post">
            <!-- メールアドレスをhiddenフィールドにセット -->
            <input type="hidden" name="newMail" value="<%= newMail %>">

            <div class="login-input">
                <label for="password" class="text-form">パスワード</label>
                <input name="newPassword" type="password" id="password" placeholder="🔒Password" class="input-form" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>
            </div>
            <div class="login-input">
                <label for="confirmPassword" class="text-form">パスワード確認</label>
                <input name="confirmPassword" type="password" id="confirmPassword" placeholder="🔒Password" class="input-form" pattern="[!-~]{8,}" title="8文字以上で入力してください。" required>
            </div>
            <input type="submit" value="変更" class="submit-button">
        </form>

        <button id="showPasswordButton">表示</button>
    </div>

</body>
<script type="text/javascript" src="../js/password_option.js"></script>
</html>
