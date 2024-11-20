<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>入場券購入一覧</title>
</head>
<body>
    <div class="container">
        <h2>入場券購入一覧</h2>

        <c:if test="${not empty purchaseList}">
        <input type="checkbox" id="target" onchange="targeted()">
        <div class="purchaselist">
        	<c:forEach var="purchase" items="${purchaseList}">
            	<table border="1">
                	<tr>
                    	<th>施設名</th><td>${purchase.fac_name}</td>
                	</tr>
                	<tr>
                    	<th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_adlt_tkt}枚]</td>
                	</tr>
                	<tr>
                    	<th>購入日</th><td>${purchase.time_pur}</td>
                	</tr>
                    <tr>
                        <th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td>
                    </tr>
                </table>
            </c:forEach>
           </div>
        </c:if>
        <c:if test="${empty purchaseList}">
           <p>購入一覧はありません。</p>
        </c:if>




        <a href="購入ページへのリンク">戻る</a>
    </div>
    <script type="text/javascript">
    function targeted(){
    	if (document.getElementById("target").checked) {

    	  } else {
    	    // チェックボックスがOFFのときの処理
    	  }
    }

    </script>
</body>
</html>
