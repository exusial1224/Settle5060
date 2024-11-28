<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>リセール一覧</title>
</head>
<body>
    <div class="container">
        <h2>リセール一覧</h2>

        <c:if test="${not empty resaleList}">
            <table border="1">
                <tr>
                    <th>施設名</th>
                    <th>日付</th>
                    <th>開始時間</th>
                    <th>終了時間</th>
                </tr>
                <c:forEach var="resale" items="${resaleList}">
                    <tr>
                        <td>${resale.fac_name}</td>
                        <td>${resale.bus_date}</td>
                        <td>${resale.start_time}</td>
                        <td>${resale.end_time}</td>
                        <td>
                            <form action="ResaleCancel" method="post">
                            	<input type="hidden" name="rsle_id" value="${resale.rsle_id}" />
                                <input type="submit" value="キャンセル" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${empty resaleList}">
            <p>リセール一覧はありません。</p>
        </c:if>

        <a href="TopDisplay.java">戻る</a>
    </div>
</body>
</html>
