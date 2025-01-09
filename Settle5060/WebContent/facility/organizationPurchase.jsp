<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>団体来場者情報登録</title>
</head>
<body>
    <div class="container">
        <h1>団体来場者情報登録</h1>
        <form action="OrganizationPurchaseConfirm" method="post">
            <input type="text" name="groupName" placeholder="団体名" required><br>
            <input type="text" name="representativeName" placeholder="代表者名" required><br>
            <input type="text" name="phoneNumber" placeholder="電話番号" required><br>
            <input type="number" name="adultsCount" placeholder="大人の人数" min="0" required><br>
            <input type="number" name="childrenCount" placeholder="小人の人数" min="0" required><br>

            <div class="buttons">
                <input type="button" value="戻る" onclick="history.back();">
                <input type="submit" value="次へ">
            </div>
        </form>
    </div>
</body>
</html>
