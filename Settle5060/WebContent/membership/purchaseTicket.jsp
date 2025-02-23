<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

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
        <div class="ticketinfo" id="ticketinfo">
            	<table class="table2" border="1">
                	<tr>
                    	<th>施設名</th><td>${ticket.fac_name}</td><th>入場日</th><td>${ticket.bus_date}</td>
                	</tr>
                	<tr>
                    	<th>枚数</th><td>[大人：${ticket.num_adlt_tkt}枚][小人：${ticket.num_chld_tkt}枚]</td><th>入場時間</th><td>${ticket.start_time}～${ticket.end_time}</td>
                	</tr>
                </table>
        </div>
        <c:choose>
        <c:when test="${ticket.rsv_admitted == false }">
        <img class="qr" alt="入場券QRコード" src="img/qr_code.png">
        <hr>
        <form action="Ticketcancel" method="get" style="display: inline;">
            <input type="hidden" name="pur_id" value="${ticket.pur_id}">
            <button type="submit" class="cancel-btn"
            ${isExpired ? "disabled" : ""}>入場キャンセル</button>

        </form>
        </c:when>
        <c:otherwise>
        <hr>
        <a href="Purchaselist">戻る</a><p>※購入履歴は使用後数ヶ月で削除</p>
        </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
<%@include  file="footer.jsp"%>
