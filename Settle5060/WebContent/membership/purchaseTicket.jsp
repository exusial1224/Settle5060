<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--<%@ include file="header.jsp" %>-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>入場券購入一覧</title>
</head>
<body>
    <div class="container">
        <h2>入場券情報</h2>
        <c:if test="${not empty ticket}">
        <p><label for="targetselect">入場前のみ表示する</label><input type="checkbox" id="targetselect" onclick="targeted()"><label for="down">降順</label><input type="radio" id="down" name="updown" checked><label for="up">昇順</label><input type="radio" id="up" name="updown"></p>
        <p><select>
        <option>施設名</option>
        </select><input type="text"><button>検索</button></p>
        <div class="ticketinfo" id="ticketinfo">
            	<table border="1">
                	<tr>
                    	<th>施設名</th><td>${ticket.fac_name}</td><th>購入日</th><td>${ticket.time_pur}</td>
                	</tr>
                	<tr>
                    	<th>枚数</th><td>[大人：${ticket.num_adlt_tkt}枚][小人：${ticket.num_chld_tkt}枚]</td><th>入場時間</th><td>${ticket.start_time}～${ticket.end_time}</td>
                	</tr>
                </table>
              </a>

        </div>
        <hr>

        </c:if>
        <c:if test="${empty ticket}">
           <p id="nullmessage">入場券の購入一覧はありません。</p>
        </c:if>


		<p style=color.black>${target}</p>

        <a href="購入ページへのリンク">戻る</a><p>購入履歴は使用後数ヶ月で削除</p>
    </div>

</body>
</html>
<!--<%@include  file="footer.jsp"%>-->
