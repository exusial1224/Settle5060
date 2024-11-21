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
    <script >
    var target = false;
    alert(target)
    function targeted(){
    	let tselect = document.querySelector("#targetselect")
    	if (tselect.checked) {
			target = true;
			document.getElementById("purchaselist").innerHTML='<div class="purchaselist" id="purchaselist"><c:forEach var="purchase" items="${purchaseList}"><input type="checkbox" id="select" onchange="selectlist()"><label for="select"><table border="1"><tr><th>2施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td></tr><tr><th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td></tr></table></label></c:forEach></div>';
			 alert(target)
    	  } else {
    	    // チェックボックスがOFFのときの処理
    		target = false;
			document.getElementById("purchaselist").innerHTML='<div class="purchaselist" id="purchaselist">
        	<c:forEach var="purchase" items="${purchaseList}">
         	 <input type="checkbox" id="select" onchange="selectlist()">
       	 <label for="select">
           	<table border="1">
               	<tr>
                   	<th>1施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td>
               	</tr>
               	<tr>
                   	<th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td>
               	</tr>
               </table>
             </label>
             	<p id="nullmessage">利用可能な入場券の購入一覧はありません。</p>
           </c:forEach>
       </div>';
    	  }
    	window.location.reload();
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
          	 <input type="checkbox" id="select" onchange="selectlist()">
        	 <label for="select">
            	<table border="1">
                	<tr>
                    	<th>1施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td>
                	</tr>
                	<tr>
                    	<th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td>
                	</tr>
                </table>
              </label>
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
<%@include  file="footer.jsp"%>
