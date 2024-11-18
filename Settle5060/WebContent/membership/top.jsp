<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>入場券購入</title>
</head>
<body>

<script>
    function changeFacility() {
        alert("選択施設を変更します");
        location.href = "facilitySelect.jsp";
    }
</script>

<div class="container">
    <div class="title">入場券購入</div>
    <div class="facility">${facilityName} <button onclick="changeFacility()">選択施設を変更</button></div>
    <label for="dateSelect">日付を選択</label>
    <select id="dateSelect">
        <option value="">--選択--</option>
    </select>
    <div class="time-slot">タイムスロットテーブル(1日分)</div>
    <button>選択</button>

    <div class="button-group">
        <a href="Resalelist" class="button">リセール一覧</a>
        <a href="Purchasedadmissionticket" class="button">購入済入場券</a>
    </div>
</div>

</body>
</html>
