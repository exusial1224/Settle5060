<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/adminFacilitySelect.css">
<title>Insert title here</title>
</head>
<body>
<p>施設を選択してください。</p>
	<input type="text" id="fac_search" name="fac_search"><input type="submit" value="検索">
	<c:choose>
	<c:when test="${not empty all_fac}">
		<div class="facility-box">
		<table border="1">
		<c:forEach var="fac" items="${all_fac}">
			<tr><th><a href="FacilitySelectComp?fac_id=${fac.fac_id}">${fac.fac_name}</a></th></tr>
		</c:forEach>
		</table>
		</div>
	</c:when>
	<c:otherwise>
		<p>施設がありません。</p>
	</c:otherwise>
	</c:choose>

</body>
</html>