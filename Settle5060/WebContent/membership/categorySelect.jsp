<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/category_select.css">
	<link rel="stylesheet" type="text/css" href="../css/test.css">
    <meta charset="UTF-8">
    <title>カテゴリ選択</title>
</head>
<body>
	<img src="../static/img/kinkakuzi2.png" class="main-img" alt="金閣寺の写真">
	<h1 class="title-text">SETTLEで<br>スマートな観光を</h1>
	<img src="../static/img/anime_zgock.gif" class="zgock-gif"alt="ズゴックgif" align="top" >
	<div class="container">
	    <form action="CategorySelect" method="get">
	        <label for="categorySelect"></label>
	        <select name="category" id="categorySelect" class="input-form">
	        	<option value="">カテゴリを選択</option>
	            <option value="0">温泉</option>
	            <option value="1">寺・寺院</option>
	            <option value="2">博物館</option>
	            <option value="3">スキー場</option>
	            <option value="4">水族館</option>
	            <option value="5">動物園</option>
	            <option value="6">果樹園</option>
	            <option value="7">レジャー</option>
	            <option value="8">その他</option>
	        </select>
	        <button type="submit" class="but">施設を表示</button>
	    </form>
	    <h2>今ホットな目的地</h2>
		    <img src="../static/img/619763.jpg" class="sub-img-harf" alt="東京の写真">
		    <img src="../static/img/619709.jpg" class="sub-img-harf" alt="大阪の写真">
		    <img src="../static/img/691432.jpg" class="sub-img-quarter" alt="福岡の写真">
		    <img src="../static/img/971332.jpg" class="sub-img-quarter" alt="京都の写真">
		    <img src="../static/img/972557.jpg" class="sub-img-quarter" alt="横浜の写真">
	</div>
</body>
</html>
