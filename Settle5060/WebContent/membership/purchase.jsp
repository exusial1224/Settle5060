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
        <h2>予約内容確認</h2>
        <p>選択した施設名: <strong>${facilityName}</strong></p>
        <p>選択した時間帯: <strong>${startTime} ～ ${endTime}</strong></p>

        <form action="Purchase" method="post">
            <p>枚数</p>
            <table>
                <tr>
                    <td>大人</td>
                    <td>1人 <strong>${adultPrice}円</strong></td>
                    <td><input type="number" name="adultCount" min="0" max="${remainingNum}" required></td>
                </tr>
                <tr>
                    <td>小人</td>
                    <td>1人 <strong>${childPrice}円</strong></td>
                    <td><input type="number" name="childCount" min="0" max="${remainingNum}" required></td>
                </tr>
            </table>
            <p>残り: <strong>${remainingNum}人</strong></p>
            <p>※残り枚数を超えないように入力してください。</p>

            <div>
                <button type="button" onclick="history.back()">戻る</button>
                <button type="submit">次へ</button>
            </div>
        </form>
    </div>
</body>
</html>
<%@ include file="footer.jsp" %>
