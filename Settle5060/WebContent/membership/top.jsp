<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<link rel="stylesheet" type="text/css" href="../css/generic.css">

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
    <div class="title-text">入場券購入</div>
    <hr>
    <div class="facility">
    ${facilityName}
    <button onclick="location.href='ChangeFacility'">選択施設を変更</button>
	</div>

    <form id="dateForm" action="DateSelect" method="post">
        <label for="dateSelect">日付を選択</label>
        <select id="dateSelect" name="selectedDate">
            <option value="">--選択--</option>
        </select>
        <button type="button" onclick="submitDate()">選択</button>
    </form>
<%
    java.util.List<bean.Slot> timeSlots = (java.util.List<bean.Slot>) session.getAttribute("timeSlots");
    String selectedDateStr = (String) session.getAttribute("selectedDate"); // String型で取得

    java.sql.Date selectedDate = null;
    if (selectedDateStr != null) {
        selectedDate = java.sql.Date.valueOf(selectedDateStr); // Stringをjava.sql.Dateに変換
    }

    if (timeSlots != null && selectedDate != null) {
%>
        <form action="SlotSelect" method="post">

        <h3>選択した施設名: <%= session.getAttribute("facilityName") %></h3>
        <h3>選択した日付: <%= selectedDate %></h3>


        <h4>時間帯を選択してください</h4>
        <table border="1">
		    <tr>
		        <th>時間帯</th>
		        <th>価格</th>
		        <th>残り枚数</th>
		        <th>選択</th>
		    </tr>
		    <% for (Object obj : timeSlots) {
		        bean.SlotExp slot = (bean.SlotExp) obj;
		    %>
		        <tr>
		            <td><%= slot.getStart_time() %> ～ <%= slot.getEnd_time() %></td>
		            <td><%= slot.getSl_price() %>円</td>
		            <td><%= slot.getRemain() %>枚</td>
		            <td>
		                <input type="radio" name="selectedSlotId" value="<%= slot.getSl_id() %>" <%= slot.getRemain() == 0 ? "disabled" : "" %>>
		            </td>
		        </tr>
		    <% } %>
		</table>
		<input type="submit" value="決定">
    	</form>
<%
    } else {
%>
        <p>データが見つかりません。</p>
<%
    }
%>


    <div class="button-group">
        <a href="Resalelist" class="button">リセール一覧</a>
        <a href="Purchaselist" class="button">購入済入場券</a>
    </div>
</div>

</body>
</html>
