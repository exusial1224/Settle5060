<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/top.css">

    <meta charset="UTF-8">
    <title>入場券購入</title>
    <script>
        function populateDateSelect() {
            const dateSelect = document.getElementById("dateSelect");
            const currentDate = new Date();
            const twoMonthsLater = new Date();
            twoMonthsLater.setMonth(currentDate.getMonth() + 2);

            while (currentDate <= twoMonthsLater) {
                const option = document.createElement("option");
                option.value = currentDate.toISOString().split("T")[0]; // "YYYY-MM-DD"形式
                option.textContent = currentDate.toISOString().split("T")[0];
                dateSelect.appendChild(option);

                currentDate.setDate(currentDate.getDate() + 1);
            }
        }

        window.onload = function () {
            populateDateSelect();
        };

        function submitDate() {
            const selectedDate = document.getElementById("dateSelect").value;
            if (!selectedDate) {
                alert("日付を選択してください。");
                return;
            }

            const form = document.getElementById("dateForm");
            form.submit();
        }
    </script>
</head>
<body>

<div class="container">
    <div class="title-text">団体来場者情報登録</div>
    <hr>

    <div id="first-lern">
	    <form id="dateForm" action="DateSelect" method="post">
	        <label for="dateSelect">日付を選択</label>
	        <select id="dateSelect" name="selectedDate">
	            <option value="">--選択--</option>
	        </select>
	        <button type="button" onclick="submitDate()">選択</button>
	    </form>
    </div>
<%
    java.util.List<bean.Slot> timeSlots = (java.util.List<bean.Slot>) session.getAttribute("timeSlots");
    String selectedDateStr = (String) session.getAttribute("selectedDate");

    java.sql.Date selectedDate = null;
    if (selectedDateStr != null) {
        selectedDate = java.sql.Date.valueOf(selectedDateStr);
    }

    if (timeSlots != null && selectedDate != null) {
%>
        <form action="OrganizationPurchase" method="post">

        <h4>時間帯を選択してください</h4>
        <div class="sel-slot-scroll">
	        <table border="1" id="sel-slot-table">
			    <tr>
			        <th class="sticky">時間帯</th>
			        <th class="sticky">残り枚数</th>
			    </tr>
			    <% for (Object obj : timeSlots) {
			        bean.SlotExp slot = (bean.SlotExp) obj;
			    %>
			        <tr>
			            <td><%= slot.getStart_time() %> ～ <%= slot.getEnd_time() %></td>
			            <td><%= slot.getRemain() %>枚</td>
			            <td>
			                <input type="radio" name="selectedSlotId" value="<%= slot.getSl_id() %>" <%= slot.getRemain() == 0 ? "disabled" : "" %>>
			            </td>
			        </tr>
			    <% } %>
			</table>
		<input type="submit" value="決定">
		</div>
    	</form>
<%
    } else {
%>
        <p>データが見つかりません。</p>
<%
    }
%>


    <div id="second-lern">
    	<form action="Resalelist">
        	<input type="submit" value="戻る" class="second-button">
        </form>
    </div>
</div>

</body>
</html>
