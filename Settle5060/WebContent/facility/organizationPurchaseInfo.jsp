<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<!-- <link rel="stylesheet" type="text/css" href="../css/top.css">  -->

    <meta charset="UTF-8">
    <title>入場券購入</title>

</head>
<body>

<div class="container">
    <div class="title-text">団体来場者情報登録</div>
    <hr>

    <div id="first-lern">
	    <form id="dateForm" action="DateSelect" method="post">
	        <label for="dateSelect">日付を選択</label>
	        <input type="date" id="dateSelect" name="selectedDate" value="<%=request.getAttribute("selectdate") %>">

	        <button>選択</button>
	    </form>
    </div>
    <c:if test="${not empty slotdata}">
        <form action="OrganizationPurchase" method="post">
          <h4>時間帯を選択してください</h4>
        <div class="sel-slot-scroll">
	        <table border="1" id="sel-slot-table">
			    <tr>
			        <th class="sticky">時間帯</th>
			        <th class="sticky">残り枚数</th>
			    </tr>
			        <tr>
			        <c:forEach var="slotdatalist" items="${slotdata}">
			            <td>${slotdatalist.start_time} ～ ${slotdatalist.end_time}</td>
			            <td>${slotdatalist.remain}枚</td>
			            <td>
			                <input type="radio" name="selectedSlotId" value="${slotdatalist.sl_id}" >
			            </td>
			         </c:forEach>
			        </tr>
			</table>
		<input type="submit" value="決定">
		</div>
    	</form>
    	</c:if>
		<c:if test="${empty slotdata}">
        <p>データが見つかりません。</p>
        </c:if>



    <div id="second-lern">
    	<form action="Resalelist">
        	<input type="submit" value="戻る" class="second-button">
        </form>
    </div>
</div>
    <script>
    window.onload = function(){
            const today = new Date();
            const data = dateFormat(today,'YYYY-MM-DD');
            const field = document.getElementById("dateSelect");
        	console.log(field.value);
        	const select = <%=request.getAttribute("selectdate") %>;
        	if(!select){
        		field.value = data;
        	}else{
        		field.value = select;
        	}
        	minsetting();
    }
    function dateFormat(today, format){
        format = format.replace("YYYY", today.getFullYear());
        format = format.replace("MM", ("0"+(today.getMonth() + 1)).slice(-2));
        format = format.replace("DD", ("0"+ today.getDate()).slice(-2));
        return format;
    }
    function minsetting(){
    	const field = document.getElementById("dateSelect");
    	const data = dateFormat(new Date(),'YYYY-MM-DD');
    	field.setAttribute("min", data);
    }
    </script>
</body>
</html>
