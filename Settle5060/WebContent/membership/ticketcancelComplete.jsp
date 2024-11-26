<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--<%@ include file="header.jsp" %>-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>入場券購入一覧</title>
</head>
<body>
    <div class="container">
	<c:choose>
	<c:when test="${not empty cancelcomp}">
		<h2>キャンセルが完了しました。</h2>
	</c:when>
	<c:otherwise>
		<h2>キャンセルに失敗しました。<br>もう一度お試し下さい。</h2>
	</c:otherwise>
	</c:choose>
		<a href="top.jsp">トップへ</a>
	</div>

</body>
</html>
<!--<%@include  file="footer.jsp"%>-->
