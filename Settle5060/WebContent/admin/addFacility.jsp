<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="adminhead.jsp" %>
<body>
<div class="form-group">
<form id="AddNewFacilityComp" action="AddNewFacilityComp" method="post">
	<div class="textinput">
		<p>企業名:${fac_data.co_name}</p>
	</div>
	<div class="textinput">
		<p>施設名:${fac_data.fac_name}</p>
	</div>
	<div class="textinput">
		<p>住所:${fac_data.fac_address}</p>
	</div>
	<div class="textinput">
		<p>電話番号:${fac_data.fac_tel}</p>
	</div>
	<div class="textinput">
		<p>メールアドレス:${fac_data.fac_mail}</p>
	</div>
	<a href="AddNewFacilityDisplay">戻る</a><button>追加</button>
</form>
</div>
</body>
</html>