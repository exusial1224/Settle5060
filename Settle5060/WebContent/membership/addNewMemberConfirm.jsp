<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/generic.css">
    <title>会員情報登録確認</title>
</head>
<body>
    <div class="container">
        <h1 class="title-text">新規会員登録確認</h1>
        <hr>
        <div class="error-text">${error}</div>
        <form id="registrationForm" action="AddNewMember" method="post">

            <div class="form-inline">
                <div class="form-cfm">
                    <p class="text-confirm"><label for="name" class="form-element">氏　　名　　</label></p>
                    <span class="input-confirm"><%= request.getParameter("name") %></span>
                </div><hr class="hr-confirm">
                <div class="form-cfm">
                    <p class="text-confirm"><label for="mail" class="form-element">メールアドレス</label></p>
                    <span class="input-confirm"><%= request.getParameter("mail") %></span>
                </div><hr class="hr-confirm">
                <div class="form-cfm">
                    <p class="text-confirm"><label for="tel" class="form-element">電　話　番　号</label></p>
                    <span class="input-confirm"><%= request.getParameter("tel") %></span>
                </div><hr class="hr-confirm">
                <div class="form-cfm">
                    <p class="text-confirm"><label for="address" class="form-element">住所</label></p>
                    <span class="input-confirm"><%= request.getParameter("address") %></span>
                </div><hr class="hr-confirm">
                <div class="form-cfm">
                    <p class="text-confirm"><label for="birth" class="form-element">生年月日(8桁)</label></p>
                    <span class="input-confirm"><%= request.getParameter("birth") %></span>
                </div><hr class="hr-confirm">
                 <p class="title">以下の内容で登録してよろしいですか？</p>
            </div>

            <div class="infor-change">
                <input type="hidden" name="mail" value="<%= request.getParameter("mail") %>">
                <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
                <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
                <input type="hidden" name="tel" value="<%= request.getParameter("tel") %>">
                <input type="hidden" name="address" value="<%= request.getParameter("address") %>">
                <input type="hidden" name="birth" value="<%= request.getParameter("birth") %>">
                <input type="button" value="戻る" onclick="history.back();" class="back-to-the-future">
                <input type="submit" value="完了" class="infor-change-btn">
            </div>
        </form>
    </div>
</body>
</html>
