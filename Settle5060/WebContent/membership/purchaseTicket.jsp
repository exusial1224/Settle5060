<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>入場券購入一覧</title>
</head>
<body>
    <div class="container">
        <h2>入場券情報</h2>
        <div class="ticketinfo" id="ticketinfo">
            	<table border="1">
                	<tr>
                    	<th>施設名</th><td>${ticket.fac_name}</td><th>購入日</th><td>${ticket.time_pur}</td>
                	</tr>
                	<tr>
                    	<th>枚数</th><td>[大人：${ticket.num_adlt_tkt}枚][小人：${ticket.num_chld_tkt}枚]</td><th>入場時間</th><td>${ticket.start_time}～${ticket.end_time}</td>
                	</tr>
                </table>
        </div>
        <c:choose>
        <c:when test="${ticket.rsv_admitted == false }">
        <img alt="入場券QRコード" src="img/qr_code.png">
        <hr>
        <a href="Purchaselist">戻る</a>
        <c:choose>
          <c:when test="${isExpired}">
            <button disabled style="color: gray;">期限切れ</button>
          </c:when>
          <c:otherwise>
            <a href="Ticketcancel?pur_id=${ticket.pur_id}">入場券キャンセル</a>
          </c:otherwise>
        </c:choose>
        </c:when>
        <c:otherwise>
        <hr>
        <a href="Purchaselist">戻る</a><p>購入履歴は使用後数ヶ月で削除</p>
        </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
<%@include  file="footer.jsp"%>
