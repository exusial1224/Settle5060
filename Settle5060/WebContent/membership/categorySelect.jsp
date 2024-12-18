<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">
	<link rel="stylesheet" type="text/css" href="../css/test.css">
    <meta charset="UTF-8">
    <title>カテゴリ選択</title>
</head>
<body>
	<div class="container">
	    <h1>カテゴリを選択</h1>
	    <hr>
	    <form action="CategorySelect" method="get">
	        <label for="categorySelect">カテゴリ:</label>
	        <select name="category" id="categorySelect">
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
	        <button type="submit">施設を表示</button>
	    </form>
	</div>
</body>
</html>
