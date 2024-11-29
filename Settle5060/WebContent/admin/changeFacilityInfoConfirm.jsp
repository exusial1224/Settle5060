<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminhead.jsp" %>
<body>
<div class="form-group">
<form id="ChangeFacilityInfoComplete" action="ChangeFacilityInfoComplete" method="post">
	<div class="textinput">
		<p>企業名<input type="text" name="co_name" value="${fac_info.co_name}" readonly></p>
	</div>
	<div class="textinput">
		<p>施設名<input type="text" name="fac_name" value="${fac_info.fac_name}" readonly></p>
	</div>
	<div class="textinput">
		<p>住所<input type="text" name="fac_address" value="${fac_info.fac_address}" readonly></p>
	</div>
	<div class="textinput">
		<p>電話番号<input type="text" name="fac_tel" value="${fac_info.fac_tel}" readonly ></p>
	</div>
		<h3>以上の内容で変更しますか？</h3>
	<a href="ChangeFacilityInfoDisplay">戻る</a><button>変更</button>
</form>
</div>
</body>
</html>