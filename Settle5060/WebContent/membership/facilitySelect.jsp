<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Facility" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">
	<link rel="stylesheet" type="text/css" href="../css/test.css">
    <meta charset="UTF-8">
    <title>施設選択</title>
</head>
<body>
	<div class="container">
	    <h1>施設選択</h1>
	    <hr>
		<div class="sisetu">
	    <%
	        List<Facility> facilityList = (List<Facility>) request.getAttribute("facilityList");
	        if (facilityList != null && !facilityList.isEmpty()) {
	            for (Facility facility : facilityList) {
	                out.println("<div>");
	                out.println("<form action='FacilitySelect' method='get'>");
	                out.println("<input type='hidden' name='facilityId' value='" + facility.getFac_id() + "'>");
	                out.println("<button type='submit' class='sisetu'>" + facility.getFac_name() + "</button>");
	                out.println("</form>");
	                out.println("</div>");
	            }
	        } else {
	            out.println("<p>該当する施設がありません。</p>");
	        }
	    %>
	    </div>
	</div>
</body>
</html>
