<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminhead.jsp" %>
<body>
<div class="form-group">
<form id="AddNewFacility" action="AddNewFacility" method="post">
	<div class="textinput">
		<p>企業名<input type="text" name="co_name"></p>
	</div>
	<div class="textinput">
		<p>施設名<input type="text" name="fac_name" ></p>
	</div>
	<div class="textinput">
		<p>住所<input type="text" name="fac_address" ></p>
	</div>
	<div class="textinput">
		<p>電話番号<input type="text" name="fac_tel" ></p>
	</div>
	<div class="textinput">
		<p>メールアドレス<input type="text" name="fac_mail" ></p>
	</div>
	<div class="textinput">
		<p>パスワード<input type="password" name="password" ></p>
	</div>
	<a href="adminTop.jsp">戻る</a><button>次へ</button>
</form>
</div>
</body>
</html>