<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminhead.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
<c:when test="${not empty change_result}">
	<p>変更が完了しました</p>
</c:when>
<c:otherwise>
	<p>変更時にエラーが発生しました</p>
</c:otherwise>
</c:choose>
<a href="adminTop.jsp">トップへ</a>
</body>
</html>