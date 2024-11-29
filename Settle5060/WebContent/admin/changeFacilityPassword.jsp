<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminhead.jsp" %>
<body>
<div class="form-group">
<form id="ChangeFacilityPasswordComplete" action="ChangeFacilityPasswordComplete" method="post">
	<div class="textinput">
		<p>新パスワード<input type="password" name="new_password"></p>
	</div>
	<div class="textinput">
		<p>新パスワード再入力<input type="password" name="new_password_re"></p>
	</div>
	<a href="ChangeFacilityInfoDisplay">戻る</a><button>変更</button>
</form>
</div>
</body>
</html>