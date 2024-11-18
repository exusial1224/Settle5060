<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>会員情報登録確認</title>
</head>
<body>
    <div class="container">
        <h1>新規会員登録確認</h1>
        <form id="registrationForm" action="AddNewMember" method="post">
            <p class="form-comment">以下の内容で登録してよろしいですか？</p>
            <div class="form-inline">
                <div class="form-cfm">
                    <p class="signup-label"><label for="name" class="form-element"> 　氏　　名　　</label></p>
                    <span class="form-input"><%= request.getParameter("name") %></span>
                </div>
                <div class="form-cfm">
                    <p class="signup-label"><label for="mail" class="form-element">メールアドレス</label></p>
                    <span class="form-input"><%= request.getParameter("mail") %></span>
                </div>
                <div class="form-cfm">
                    <p class="signup-label"><label for="tel" class="form-element">電　話　番　号</label></p>
                    <span class="form-input"><%= request.getParameter("tel") %></span>
                </div>
                <div class="form-cfm">
                    <p class="signup-label"><label for="address" class="form-element">住所</label></p>
                    <span class="form-input"><%= request.getParameter("address") %></span>
                </div>
                <div class="form-cfm">
                    <p class="signup-label"><label for="birth" class="form-element">生年月日(8桁)</label></p>
                    <span class="form-input"><%= request.getParameter("birth") %></span>
                </div>
            </div>

            <div class="infor-change">
                <input type="hidden" name="mail" value="<%= request.getParameter("mail") %>">
                <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
                <input type="hidden" name="password" value="<%= request.getParameter("password") %>">
                <input type="hidden" name="tel" value="<%= request.getParameter("tel") %>">
                <input type="hidden" name="address" value="<%= request.getParameter("address") %>">
                <input type="hidden" name="birth" value="<%= request.getParameter("birth") %>">
                <input type="button" value="戻る" onclick="history.back();" class="infor-change-btn">
                <input type="submit" value="完了" class="infor-change-btn">
            </div>
        </form>
    </div>
</body>
</html>
