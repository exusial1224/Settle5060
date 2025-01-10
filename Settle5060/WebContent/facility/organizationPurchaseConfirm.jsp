<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

</head>
<body>
	<div class="container">
	    <form action="OrganizationPurchaseComplete" method="post">
            <label>団体名</label><input type="text" name="groupName" value="${groupName}" disabled><br>
            <label>代表者名</label><input type="text" name="representativeName" value="${representativeName}" disabled><br>
            <label>電話番号</label><input type="text" name="phoneNumber" value="${phoneNumber}" disabled><br>
            <label>大人の人数</label><input type="number" name="adultsCount" value="${adultsCount}"  disabled><br>
            <label>小人の人数</label><input type="number" name="childrenCount" value="${childrenCount}"  disabled><br>
            <div class="buttons">
                <input type="button" value="戻る" onclick="history.back();">
                <input type="submit" value="次へ">
            </div>
        </form>
	</div>
</body>
</html>
