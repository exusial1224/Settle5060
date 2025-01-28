<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="facilityheader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <h1>不定休館設定</h1>
    <p>×: 定休日, ✔: 不定休</p>

    <!-- 新しい不定休館日を登録するフォーム -->
    <form action="CloseDayUpdate" method="get">
        <div>
            <label for="selectedDate">日付選択:</label>
            <input type="date" id="selectedDate" name="selectedDate" required>
        </div>
        <button type="submit">更新</button>
    </form>

    <!-- 定期休館日と不定休館日を表示 -->
    <div>
        <h3>定休日:</h3>
        <h3>不定休日:</h3>
        <ul>
            <c:forEach var="closure" items="${irregularClosures}">
                <li>${closure}</li>
            </c:forEach>
        </ul>
    </div>

	    <!-- 結果の表示 -->
	<c:if test="${not empty successMessage}">
	    <div class="alert alert-success">${successMessage}</div>
	</c:if>
	<c:if test="${not empty errorMessage}">
	    <div class="alert alert-danger">${errorMessage}</div>
	</c:if>


    <a href="FacilityInfoDisplay">戻る</a>
</div>
