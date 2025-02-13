<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">
    <meta charset="UTF-8">
    <title>予約確認</title>
</head>
<body>
    <div class="container">
        <h2 class="title-text">予約内容確認</h2>
        <hr>
        <p>選択した施設名: <strong>${facilityName}</strong></p>
        <p>選択した時間帯: <strong>${startTime} ～ ${endTime}</strong></p>

        <form action="Purchase" method="post">
            <h3>枚数</h3>
            ${selectedDate}
            <table>
                <tr>
                    <td>大人</td>
                    <td>1人 <strong>${adultPrice}円</strong></td>
                    <td><input type="number" name="adultCount" min="0" max="${remainingNum}" value=0 required></td>
                </tr>
                <tr>
                    <td>小人</td>
                    <td>1人 <strong>${childPrice}円</strong></td>
                    <td><input type="number" name="childCount" min="0" max="${remainingNum}" value=0 required></td>
                </tr>
            </table>
            <p>残り: <strong>${remainingNum}人</strong></p>
            <p>※残り枚数を超えないように入力してください。</p>

            <div>
                <button type="button" onclick="history.back()" class="back-to-the-future">戻る</button>
                <input type="submit" value="つぎへ">
            </div>
        </form>
    </div>
</body>
</html>

