<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="facilityheader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<p>企業名：${facilityinfo.co_name}</p>
	<p>施設名：${facilityinfo.fac_name}</p>
	<p>メールアドレス：${facilityinfo.fac_mail}</p>
	<p>住所：${facilityinfo.fac_address}</p>
	<p>電話番号：${facilityinfo.fac_tel}</p>
 <hr>
   <a href="CloseDayDisplay">休館日設定</a>


