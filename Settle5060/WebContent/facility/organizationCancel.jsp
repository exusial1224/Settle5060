<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="entry.jsp" %>
<div name="organizationCancel-window">
	<h2>以下の購入をキャンセルしますか？</h2>
	<p>団体名：${op.org_name }様</p>
	<p>代表者名：${op.rep_name }様</p>　
	<p>電話番号：${op.org_tel }</p>
	<p>時間：${op.start_time}～${op.end_time }</p>
	<p>枚数(大人)：${op.org_tel }枚数(小人)：${op.org_tel }</p>
	<input type=submit value="キャソセル">
	<input type=submit value="キャン七ル">
	<input type=submit value="キャンセル">
	<input type=submit value="キヤンセル">
</div>
