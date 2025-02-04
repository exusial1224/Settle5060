<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/generic.css">
    <title>会員情報変更確認</title>

</head>
<body>
    <div class="container">
        <h1 class="title-text">会員情報変更確認</h1>
        <hr>
        <form id="memberChangeConfirmForm" action="InformationChange" method="post">
			<p class="form-comment">以下の内容で変更してよろしいですか？</p>
			<div class="form-inline">

				<div class="form-cfm">
		            <label for="name" class="text-form">氏　名　</label>
		            <p id="name" class="input-form">${requestScope.name}</p>
		        </div>

		        <div class="form-cfm">
		            <label for="tel"  class="text-form">電話番号 </label>
		            <p id="tel" class="input-form">${requestScope.tel}</p>
	            </div>

	            <div class="form-cfm">
		            <label for="address"  class="text-form">住所 </label>
		            <p id="address" class="input-form">${requestScope.address}</p>
	            </div>

	            <div class="form-cfm">
		            <label for="birth"  class="text-form">生年月日 </label>
		            <p id="birth" class="input-form">${requestScope.birth}</p>
	            </div>

	        </div>

			<%-- 送る値 --%>
			<input type="hidden" name="name" value="${requestScope.name}">
			<input type="hidden" name="tel" value="${requestScope.tel}">

			<input type="hidden" name="address" value="${requestScope.address}">
			<input type="hidden" name="birth" value="${requestScope.birth}">
		<div class="infor-change">
            <!-- 戻るボタン -->
            <input type="button" value="戻る" onclick="history.back();" class="back-to-the-future">

            <!-- 確定ボタン -->
            <input type="submit" value="確定" class="infor-change-btn">
        </div>
        </form>
    </div>
</body>
</html>