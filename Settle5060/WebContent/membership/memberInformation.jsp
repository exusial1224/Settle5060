<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">
	<link rel="stylesheet" type="text/css" href="../css/member_information.css">
    <title>会員情報</title>
</head>
<body>


    <div class="container">
    	<h1 class="title-text">会員情報</h1>
    	<hr>
        <table border="1" class="memberinfo-table">
            <c:choose>
                <c:when test="${not empty memberInfo}">
                    <tr><th class="text-form">名前</th><td class="memberinfo-td">${memberInfo.mbr_name}</td></tr>
                    <tr><th class="text-form">メールアドレス</th><td class="memberinfo-td">${memberInfo.mbr_mail}</td></tr>
                    <tr><th class="text-form">電話番号</th><td class="memberinfo-td">${memberInfo.mbr_tel}</td></tr>
                    <tr><th class="text-form">住所</th><td class="memberinfo-td">${memberInfo.mbr_address}</td></tr>
                    <tr><th class="text-form">生年月日</th><td class="memberinfo-td">${memberInfo.mbr_birth}</td></tr>
                    <tr><th class="text-form">登録日</th><td class="memberinfo-td">${memberInfo.mbr_reg}</td></tr>
                </c:when>
            </c:choose>
        </table>
    </div>

    <div class="member-selection">
        <form action="InformationChangeDisplay" method="post">
            <input type="hidden" name="name" value="${memberInfo.mbr_name}">
            <input type="hidden" name="tel" value="${memberInfo.mbr_tel}">
            <input type="hidden" name="mail" value="${memberInfo.mbr_mail}">
            <input type="hidden" name="address" value="${memberInfo.mbr_address}">
            <input type="hidden" name="birth" value="${memberInfo.mbr_birth}">
            <button type="submit" class="memberinfo-btn">会員情報変更</button>
        </form>
        <form action="TopDisplay" method="Get">
            <input type="hidden" name="mail" >
            <button type="submit" class="memberinfo-btn">トップページに戻る</button>
        </form>
    </div>
</body>
</html>
