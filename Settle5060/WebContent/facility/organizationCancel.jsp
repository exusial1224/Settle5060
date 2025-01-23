<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <%@ include file="entry.jsp" %>  -->

	<h2>以下の購入をキャンセルしますか？</h2>

	<p>団体名：${op.org_name }様</p>
	<p>代表者名：${op.rep_name }様</p>
	<p>電話番号：${op.org_tel }</p>
	<p>時間：${op.start_time}～${op.end_time }</p>
	<p>枚数(大人)：${op.num_adlt_tkt_gr }枚数(小人)：${op.num_chld_tkt_gr }</p>

	<input type=submit value="残す" onclick="back()">
	<input type=submit value="決定" onclick="cancel()">

<script>
var back = function(){
	location.href = "EntryDisplay";
}
var cancel = function(){
	location.href = 'OrganizationCancelComplete?org_pur_id=${op.org_pur_id }';
}

</script>