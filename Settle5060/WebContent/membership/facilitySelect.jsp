<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Facility" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/test.css">
	<link rel="stylesheet" type="text/css" href="../css/facility_select.css">
    <meta charset="UTF-8">
    <form action="CategorySelect" method="get" class="category-sel">
	        <label for="categorySelect"></label>
	        <select name="category" id="categorySelect" class="input-form">
	        	<option value="">カテゴリを選択</option>
	            <option value="0">温泉</option>
	            <option value="1">寺・寺院</option>
	            <option value="2">博物館</option>
	            <option value="3">スキー場</option>
	            <option value="4">水族館</option>
	            <option value="5">動物園</option>
	            <option value="6">果樹園</option>
	            <option value="7">レジャー</option>
	            <option value="8">その他</option>
	        </select>
	        <button type="submit" class="but">categoryを変更</button>
	    </form>

    <title>施設選択</title>

</head>
<body>
	<div class="container">
	    <h1>施設選択</h1>
		<div class="sisetu">
	    <%
	        List<Facility> facilityList = (List<Facility>) request.getAttribute("facilityList");
	        if (facilityList != null && !facilityList.isEmpty()) {
	            for (Facility facility : facilityList) {
	    %>
	                <div class="facility-one" onclick="submitForm('<%= facility.getFac_id() %>')">
	                    <form action="FacilitySelect" method="get" id="form_<%= facility.getFac_id() %>">
	                        <input type="hidden" name="facilityId" value="<%= facility.getFac_id() %>">
	                        <div class="fac-name"><h1><%= facility.getFac_name() %></h1></div>
	                        <div class="sub-text"><%= facility.getFac_address() %></div>
	                        <div class="sub-text"> TEL：<%= facility.getFac_tel() %></div>
	                        <div class="sub-text"><%= facility.getOpen_time() %> ～ <%= facility.getClose_time() %></div>
	                    </form>
	                </div>
	    <%
	            }
	        } else {
	            out.println("<p>該当する施設がありません。</p>");
	        }
	    %>
	    </div>
	</div>

	<script>
	    function submitForm(facilityId) {
	        document.getElementById("form_" + facilityId).submit();
	    }
	</script>
</body>
</html>
