<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>施設を選択してください。</p>
	<c:choose>
	<c:when test="${not empty all_fac}">
		<table>
		<c:forEach var="fac" items="${all_fac}">
			<a href="FacilitySelectComp?fac_id=${fac.fac_id}"><th>${fac.fac_name}</th></a>
		</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<p>施設がありません。</p>
	</c:otherwise>
	</c:choose>

</body>
</html>