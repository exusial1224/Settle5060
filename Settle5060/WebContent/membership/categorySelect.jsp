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
	<img src="../static/img/anime_zgock.gif" class="zgock-gif" id="zgock" alt="ズゴックgif" align="top" >
	<div id="splitZ"></div>
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
<%@ include file="footer.jsp" %>
	<script>
	document.getElementById("zgock").addEventListener("click", function() {
	    let img = this;
	    let parent = img.parentElement;
	    let splitZ = document.getElementById("splitZ");

	    if (!splitZ) {
	        console.error("a");
	        return;
	    }

	    img.classList.add("shake");
	    setTimeout(() => {
	        img.classList.remove("shake");
	        setTimeout(() => {
	            img.style.display = "none";

	            //画像追加、クラス追加
	            let newImg = document.createElement("img");
	            newImg.src = "../static/img/inja.png";
	            newImg.classList.add("replacement-img");
	            parent.appendChild(newImg);

	            //画像分割
	            let leftHalf = document.createElement("img");
	            leftHalf.src = img.src;
	            leftHalf.classList.add("split", "left");
	            leftHalf.style.clipPath = "polygon(0 0, 50% 0, 50% 100%, 0 100%)";//生成画像の分割,
	            splitZ.appendChild(leftHalf);

	            let rightHalf = document.createElement("img");
	            rightHalf.src = img.src;
	            rightHalf.classList.add("split", "right");
	            rightHalf.style.clipPath = "polygon(50% 0, 100% 0, 100% 100%, 50% 100%)";
	            splitZ.appendChild(rightHalf);

	            console.log(leftHalf, rightHalf); // デバッグ用

	            // 0.1秒後に左右に飛ばす
	            setTimeout(() => {
	                leftHalf.classList.add("moveLeft");
	                rightHalf.classList.add("moveRight");
	            }, 100);
	        }, 1000);
	    }, 2000);
	});

	</script>
</body>
</html>
