<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="adminhead.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
   <h2>管理メニュー</h2>

  <c:choose>
  <c:when test="${not empty facilityName}">
  <!-- facilityに値がある場合は、メニューを有効化 -->
   	<p>${facilityName}</p><a href="FacilitySelect">施設を変更</a>
   	<a href="ChangeFacilityInfo" id="menu-button">施設情報変更</a>
  </c:when>
  <c:otherwise>
   <!-- facilityに値がない場合は、メニューを無効化 -->
   	<p>選択されていません</p><a href="FacilitySelect">施設を選択</a>
   	<a href=# id="null-facility-button">施設情報変更</a>
  </c:otherwise>
  </c:choose>
  	<a href="AddNewFacility" id="menu-button">新規施設登録</a>
  	<a href="Logout" id="menu-button">ログアウト</a>
</div>

</body>
</html>