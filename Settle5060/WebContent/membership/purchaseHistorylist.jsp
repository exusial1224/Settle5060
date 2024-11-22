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
    <script >
    var target = false;
    alert(target)
    function targeted(){
    	let tselect = document.querySelector("#targetselect")
    	if (tselect.checked) {
    		document.getElementById("purchaselist").innerHTML='<div class="purchaselist" id="purchaselist"><c:forEach var="purchase" items="${purchaseList}"><c:if test="${purchase.rsv_admitted == false}"><a href="PurchaseTicket?"PurchaseTicket?fac_name=${purchase.fac_name}&time_pur=${purchase.time_pur}&num_adlt_tkt=${purchase.num_adlt_tkt}&num_chld_tkt=${purchase.num_chld_tkt}&start_time=${purchase.start_time}&end_time=${purchase.end_time}"><table border="1"><tr><th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td></tr><tr><th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td></tr></table></a></c:if></c:forEach></div>';

    	  } else {
    	    // チェックボックスがOFFのときの処理
  			document.getElementById("purchaselist").innerHTML='<div class="purchaselist" id="purchaselist"><c:forEach var="purchase" items="${purchaseList}"><c:choose><c:when test="${purchase.rsv_admitted == false}"><a href="PurchaseTicket?fac_name=${purchase.fac_name}&time_pur=${purchase.time_pur}&num_adlt_tkt=${purchase.num_adlt_tkt}&num_chld_tkt=${purchase.num_chld_tkt}&start_time=${purchase.start_time}&end_time=${purchase.end_time}"><table border="1"><tr><th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td></tr><tr><th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td></tr></table></a></c:when><c:otherwise><a href="PurchaseTicket?fac_name=${purchase.fac_name}&time_pur=${purchase.time_pur}&num_adlt_tkt=${purchase.num_adlt_tkt}&num_chld_tkt=${purchase.num_chld_tkt}&start_time=${purchase.start_time}&end_time=${purchase.end_time}"><p id = "admitted">使用済み</p><table border="1"><tr><th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td></tr><tr><th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td></tr></table></a></c:otherwise></c:choose></c:forEach></div>';
    	  }
    }

    </script>
    <div class="container">
        <h2>入場券購入一覧</h2>

        <c:if test="${not empty purchaseList}">
        <p><label for="targetselect">入場前のみ表示する</label><input type="checkbox" id="targetselect" onclick="targeted()"><label for="down">降順</label><input type="radio" id="down" name="updown" checked><label for="up">昇順</label><input type="radio" id="up" name="updown"></p>
        <p><select>
        <option>施設名</option>
        </select><input type="text"><button>検索</button></p>
        <div class="purchaselist" id="purchaselist">
        	<c:forEach var="purchase" items="${purchaseList}">
        	<c:choose>
        	<c:when test="${purchase.rsv_admitted == false}">
          	 <a href="PurchaseTicket?fac_name=${purchase.fac_name}&time_pur=${purchase.time_pur}&num_adlt_tkt=${purchase.num_adlt_tkt}&num_chld_tkt=${purchase.num_chld_tkt}&start_time=${purchase.start_time}&end_time=${purchase.end_time}">
            	<table border="1">
                	<tr>
                    	<th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td>
                	</tr>
                	<tr>
                    	<th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td>
                	</tr>
                </table>
              </a>
              </c:when>
              <c:otherwise>
              <a href="PurchaseTicket?fac_name=${purchase.fac_name}&time_pur=${purchase.time_pur}&num_adlt_tkt=${purchase.num_adlt_tkt}&num_chld_tkt=${purchase.num_chld_tkt}&start_time=${purchase.start_time}&end_time=${purchase.end_time}">
              <p id = "admitted">使用済み</p>
            	<table border="1">
                	<tr>
                    	<th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td>
                	</tr>
                	<tr>
                    	<th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td>
                	</tr>
                </table>
              </a>
              </c:otherwise>
              </c:choose>
            </c:forEach>
        </div>
        <hr>

        </c:if>
        <c:if test="${empty purchaseList}">
           <p id="nullmessage">入場券の購入一覧はありません。</p>
        </c:if>


		<p style=color.black>${target}</p>

        <a href="購入ページへのリンク">戻る</a>
    </div>

</body>
</html>
<!--<%@include  file="footer.jsp"%>-->
