<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/pl.css">
    <meta charset="UTF-8">
    <title>入場券購入一覧</title>
</head>

<body>
    <div class="container">
        <h2>入場券情報</h2>
        <form id="TicketcancelComplete" action="TicketcancelComplete" method="post" class="signup-form">
        <div class="ticketcancel" id="ticketcancel">
				<input type="hidden" name="pur_id" value="${ticket.pur_id}">
            	<table border="1" class="table2">
                	<tr>
                    	<th>施設名</th><td>${ticket.fac_name}</td>
                	</tr>
                	<tr>
                		<th>入場時間</th><td>${ticket.start_time}～${ticket.end_time}</td>
                	</tr>
                	<tr>
                	<th>キャンセルする枚数(大人)</th>
                	<td>
                	<select name="cancel_adlt_tkt" id="cancel_num_adlt_tkt">
						<c:forEach begin="0" end="${ticket.num_adlt_tkt}" step="1" varStatus="cancel_num_adlt_tkt">
							<option value="${cancel_num_adlt_tkt.index}" class="qwe">${cancel_num_adlt_tkt.index}</option>
						</c:forEach>
                	</select>
                	<p>現在：${ticket.num_adlt_tkt}枚</p>
                	</td>
                	</tr>
                	<tr>
                	<th>キャンセルする枚数(小人)</th>
                	<td>
                	<select name="cancel_chld_tkt" id="cancel_num_chld_tkt">
						<c:forEach begin="0" end="${ticket.num_chld_tkt}" step="1" varStatus="cancel_num_chld_tkt">
							<option value="${cancel_num_chld_tkt.index}">${cancel_num_chld_tkt.index}</option>
						</c:forEach>
                	</select>
                	<p>現在：${ticket.num_chld_tkt}枚</p>
                	</td>
                	</tr>
                </table>
        </div>
        <input type="submit" value="キャンセル">
       	</form>

        <hr>
        <a href="PurchaseTicket?pur_id=${ticket.pur_id }">戻る</a>
    </div>

</body>
</html>
<!--<%@include  file="footer.jsp"%>-->
