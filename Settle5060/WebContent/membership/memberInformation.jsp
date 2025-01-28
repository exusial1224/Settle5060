<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<html lang="ja">
<head>
	<!-- <link rel="stylesheet" type="text/css" href="../css/generic.css">  -->
	<link rel="stylesheet" type="text/css" href="../css/member_information.css">
    <title>会員情報</title>
</head>
<body>


    <div class="container">
    	<div  class="mbr_info_tab">
			<table class="mbr_info_table_tab">
				<tr><td>個人情報</td></tr>
				<tr><td><button onclick="location.href='./Purchaselist'" class="mbr_info_tab_btn_ctr">購入済入場券</button></td></tr>
				<tr><td><button onclick="location.href='./Resalelist'" class="mbr_info_tab_btn_ctr">リセール一覧</button></td></tr>
				<tr><td><button onclick="location.href='./InformationChangeDisplay'" class="mbr_info_tab_btn_btm">会員情報変更</button></td></tr>

			</table>

    	</div>
    	<div class="mbr_info">
	    	<h1 class="title-text">個人情報</h1>
	    	<a>個人情報の表示や変更が行えます。</a>
	    	<hr>
	        <table class="memberinfo-table">
	            <c:choose>
	                <c:when test="${not empty memberInfo}">
	                    <tr><th align="left" class="text">名前</th><td class="memberinfo-td">${memberInfo.mbr_name}</td></tr>
	                    <tr><th align="left" class="text">メールアドレス</th><td class="memberinfo-td">${memberInfo.mbr_mail}</td></tr>
	                    <tr><th align="left" class="text">電話番号</th><td class="memberinfo-td">${memberInfo.mbr_tel}</td></tr>
	                    <tr><th align="left" class="text">住所</th><td class="memberinfo-td">${memberInfo.mbr_address}</td></tr>
	                    <tr><th align="left" class="text">生年月日</th><td class="memberinfo-td">${memberInfo.mbr_birth}</td></tr>
	                    <tr><th align="left" class="text">登録日</th><td class="memberinfo-td">${memberInfo.mbr_reg}</td></tr>
	                </c:when>
	            </c:choose>
	        </table>
		</div>
    </div>

</body>
</html>
