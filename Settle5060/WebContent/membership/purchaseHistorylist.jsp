<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic2.css">
	<link rel="stylesheet" type="text/css" href="../css/pl.css">
    <meta charset="UTF-8">
    <title>入場券購入一覧</title>
</head>
<body>

    <script >
    var target = false;
    function targeted(){
    	let tselect = document.querySelector("#targetselect")
    	if (tselect.checked) {
    		document.getElementById("purchaselist").innerHTML='<div class="purchaselist" id="purchaselist"><c:forEach var="purchase" items="${purchaseList}"><c:if test="${purchase.rsv_admitted == false}"><a href="javascript:urlmaker(${purchase.pur_id});"><table border="1"><tr><th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td></tr><tr><th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td></tr></table></a></c:if></c:forEach></div>';

    	  } else {
    	    // チェックボックスがOFFのときの処理
  			document.getElementById("purchaselist").innerHTML='<div class="purchaselist" id="purchaselist"><c:forEach var="purchase" items="${purchaseList}"><c:choose><c:when test="${purchase.rsv_admitted == false}"><a href="javascript:urlmaker(${purchase.pur_id});"><table border="1"><tr><th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td></tr><tr><th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td></tr></table></a></c:when><c:otherwise><a href="javascript:urlmaker(${purchase.pur_id});"><p id = "admitted">使用済み</p><table border="1"><tr><th>施設名</th><td>${purchase.fac_name}</td><th>購入日</th><td>${purchase.time_pur}</td></tr><tr><th>枚数</th><td>[大人：${purchase.num_adlt_tkt}枚][小人：${purchase.num_chld_tkt}枚]</td><th>入場時間</th><td>${purchase.start_time}～${purchase.end_time}</td></tr></table></a></c:otherwise></c:choose></c:forEach></div>';
    	  }
    }
    async function urlmaker(message) {
      const msgUint8 = new TextEncoder().encode(message); // (utf-8 の) Uint8Array にエンコード
  	  const hashBuffer = await crypto.subtle.digest("SHA-256", msgUint8); // メッセージのハッシュ値を取得
  	  const hashArray = Array.from(new Uint8Array(hashBuffer)); // バッファーをバイト列に変換
  	  const hashHex = hashArray.map((b) => b.toString(16).padStart(2, "0")).join(""); // バイト列を 16 進文字列に変換
  	  location.href = "PurchaseTicket?pur_id="+message;

    }
    function selectsearchtype(){
    	const search_type = document.getElementById("search_type");

    	if(search_type == "fac_name"){
    		console.log(search_type.value);
    		search_type.onclick("");
    	}else if(search_type == "date"){
    		console.log(search_type.value);
    		search_type.onclick("dateselect()");
    	}
    }
	function dateselect(){
		console.log("事項");

	}
    function search(){
    	const search_type = document.getElementById("search_type");
    	console.log(search_type.value);
    	const keyword = document.getElementById("keyword");
    	console.log(keyword.value);
    	location.href="PurchaseListSearch?search_type="+search_type.value+"&keyword="+keyword.value;

    }
    </script>
    <div class="container">
        <h2 class="title-text">入場券購入一覧</h2>
		<hr>
        <c:if test="${not empty purchaseList}">
        <p><label for="targetselect">入場前のみ表示する</label><input type="checkbox" id="targetselect" onclick="targeted()"><label for="down">降順</label><input type="radio" id="down" name="updown" checked><label for="up">昇順</label><input type="radio" id="up" name="updown"></p>
        <p><select id="search_type" onchange="selectsearchtype()">
        <option value="fac_name">施設名</option>
        <option value="date">入場日時</option>
        </select><input type="text" id="keyword" name="formtype">
        <button onclick="search()">検索</button></p>
        <div class="purchaselist" id="purchaselist">
        	<c:forEach var="purchase" items="${purchaseList}">
        	<c:choose>
        	<c:when test="${purchase.rsv_admitted == false}">
  <a href="javascript:urlmaker(${purchase.pur_id});">
    <table border="1" class="purlis">
      <tr>
        <th>施設名</th>
        <td>${purchase.fac_name}</td>
        <th>入場日時</th>
        <td>${purchase.bus_date} ${purchase.start_time}～${purchase.end_time}</td>
      </tr>
      <tr>
        <th>枚数</th>
        <td>大人：${purchase.num_adlt_tkt}枚</td><td>小人：${purchase.num_chld_tkt}枚</td>
      </tr>
    </table>
  </a>
</c:when>
<c:otherwise>
  <a href="javascript:urlmaker(${purchase.pur_id});">
    <p id="admitted">使用済み</p>
    <table class= "purlis" border="1">
      <tr>
        <th>施設名</th>
        <td>${purchase.fac_name}</td>
        <th>入場日時</th>
        <td>${purchase.bus_date} ${purchase.start_time}～${purchase.end_time}</td>
      </tr>
      <tr>
        <th>枚数</th>
        <td>大人：${purchase.num_adlt_tkt}枚</td><td>小人：${purchase.num_chld_tkt}枚</td>
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
           <hr>
        </c:if>


		<p style=color.black>${target}</p>

        <a href="top.jsp">戻る</a><p>※購入履歴は使用後数ヶ月で削除</p>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/black_out.js"></script>
</body>
</html>

