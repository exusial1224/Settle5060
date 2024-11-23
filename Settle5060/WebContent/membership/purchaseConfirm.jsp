<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>予約確認</title>
</head>
<body>
    <div class="container">
        <h2>予約確認画面</h2>
        <table border="1">
            <tr>
                <td>選択した施設名</td>
                <td><strong>${facilityName}</strong></td>
            </tr>
            <tr>
                <td>選択した時間帯</td>
                <td><strong>${startTime} ～ ${endTime}</strong></td>
            </tr>
            <tr>
                <td>枚数</td>
                <td>
                    大人: <strong>${adultCount}枚 (${adultTotalPrice}円)</strong><br>
                    小人: <strong>${childCount}枚 (${childTotalPrice}円)</strong>
                </td>
            </tr>
            <tr>
                <td>合計金額</td>
                <td><strong>${totalPrice}円</strong></td>
            </tr>
        </table>


        <form action="PurchaseComplete" method="post">
            <input type="hidden" name="adultCount" value="${adultCount}">
            <input type="hidden" name="childCount" value="${childCount}">
            <input type="hidden" name="adultPrice" value="${adultPrice}">
            <input type="hidden" name="childPrice" value="${childPrice}">
            <input type="hidden" name="adultTotalPrice" value="${adultTotalPrice}">
            <input type="hidden" name="childTotalPrice" value="${childTotalPrice}">
            <input type="hidden" name="totalPrice" value="${totalPrice}">

            <button type="button" onclick="history.back()">戻る</button>
            <button type="submit">確定</button>
        </form>
    </div>
</body>
</html>
<%@ include file="footer.jsp" %>
