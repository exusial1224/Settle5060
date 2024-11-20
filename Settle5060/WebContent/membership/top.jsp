<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
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
    <div class="title">入場券購入</div>
    <div class="facility">
        <%= session.getAttribute("facilityName") %>
        <button onclick="changeFacility()">選択施設を変更</button>
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
    java.sql.Date selectedDate = (java.sql.Date) session.getAttribute("selectedDate");

    if (timeSlots != null && selectedDate != null) {
%>
        <form action="SlotSelect" method="post">
        <h3>選択した施設名: <%= session.getAttribute("facilityName") %></h3>
        <h3>選択した日付: <%= session.getAttribute("selectedDate") %></h3>

        <h4>時間帯を選択してください</h4>
        <table border="1">
            <tr>
                <th>時間帯</th>
                <th>価格</th>
                <th>選択</th>
            </tr>
            <% for (bean.Slot slot : timeSlots) { %>
                <tr>
                    <td><%= slot.getStart_time() %> ～ <%= slot.getEnd_time() %></td>
                    <td><%= slot.getSl_price() %>円</td>
                    <td>
                        <input type="radio" name="selectedSlotId" value="<%= slot.getSl_id() %>">
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
<%@ include file="footer.jsp" %>