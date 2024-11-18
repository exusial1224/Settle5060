<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>会員情報変更確認</title>

</head>
<body>
    <div class="container">
        <h1>会員情報変更確認</h1>
        <form id="memberChangeConfirmForm" action="InformationChange" method="post">
			<p class="form-comment">以下の内容で変更してよろしいですか？</p>
			<div class="form-inline">

				<div class="form-cfm">
		            <label for="name" class="form-element">氏　名　</label>
		            <p id="name" class="form-input">${requestScope.name}</p>
		        </div>

		        <div class="form-cfm">
		            <label for="tel"  class="form-element">電話番号 </label>
		            <p id="tel" class="form-input">${requestScope.tel}</p>
	            </div>

	            <div class="form-cfm">
		            <label for="address"  class="form-element">住所 </label>
		            <p id="address" class="form-input">${requestScope.address}</p>
	            </div>

	            <div class="form-cfm">
		            <label for="birth"  class="form-element">生年月日 </label>
		            <p id="birth" class="form-input">${requestScope.birth}</p>
	            </div>

	        </div>

			<%-- 送る値 --%>
			<input type="hidden" name="name" value="${requestScope.name}">
			<input type="hidden" name="tel" value="${requestScope.tel}">

			<input type="hidden" name="address" value="${requestScope.address}">
			<input type="hidden" name="birth" value="${requestScope.birth}">
		<div class="infor-change">
            <!-- 戻るボタン -->
            <input type="button" value="戻る" onclick="history.back();" class="infor-change-btn">

            <!-- 確定ボタン -->
            <input type="submit" value="確定" class="infor-change-btn">
        </div>
        </form>
    </div>
</body>
</html>