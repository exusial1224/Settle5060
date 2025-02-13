<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/top.css">
    <meta charset="UTF-8">
    <script src="../js/rowClickHandler.js" defer></script>
</head>
<body>
<div id="blackout" style="display: none;"></div>
<div id="popup" style="display: none;">
    <h2 class="popup-title">日付を選択</h2>
    <div class="popup-content">
        <select id="popupDateSelect" name="popupDateSelect">
            <option value="">--日付を選択--</option>
        </select>
    </div>
    <div class="popup-buttons">
        <button id="popupSubmit">確定</button>
        <button id="popupCancel">キャンセル</button>
    </div>
</div>

<div class="container">
    <div id="first-lern">
        <h1 id="fac-name">
            ${facilityName}
        </h1>
        <button onclick="location.href='ChangeFacility'" class="change-btn">選択施設を変更</button>
        <form id="dateForm" action="DateSelect" method="post">
            <label for="dateInput">日付を選択</label>
            <input id="dateInput" type="text" name="selectedDate" readonly placeholder="日付を選択" value="${selectedDate }"  onclick="showPopup()" />
            <button type="submit">選択</button>
        </form>
    </div>
    <%
        String message = (String) session.getAttribute("message");
        if (message != null) {
    %>
    <div class="message-box">
        <p><%= message %></p>
    </div>
    <%
            session.removeAttribute("message");
        }
    %>

    <%-- 時間帯選択フォームa --%>
    <%
        java.util.List<bean.Slot> timeSlots = (java.util.List<bean.Slot>) session.getAttribute("timeSlots");
        String selectedDateStr = (String) session.getAttribute("selectedDate");
        java.sql.Date selectedDate = null;
        if (selectedDateStr != null) {
            selectedDate = java.sql.Date.valueOf(selectedDateStr);
        }
        if (timeSlots != null && selectedDate != null) {
    %>
    <form id="slotForm" action="SlotSelect" method="post">
        <input type="hidden" id="selectedSlotId" name="selectedSlotId">
        <div class="sel-slot-scroll">
            <table border="1" id="sel-slot-table">
                <tr>
                    <th class="sticky">時間帯</th>
                    <th class="sticky current-price">現在の価格</th>
                    <th class="sticky">残り枚数</th>
                    <th class="sticky resale" >リセール予約</th>
                </tr>
                <% for (Object obj : timeSlots) {
                    bean.SlotExp slot = (bean.SlotExp) obj;
                %>
                <tr class="selectable-row" data-slot-id="<%= slot.getSl_id() %>">
                    <td><%= slot.getStart_time() %> ～ <%= slot.getEnd_time() %></td>
                    <td><%= slot.getSl_price() %>円</td>
                    <td><%= slot.getRemain() %>枚</td>
                    <td>
                        <input type="checkbox" name="selectedSlotId" class="resale-check" value="<%= slot.getSl_id() %>" <%= slot.getRemain() == 0 ? "" : "disabled" %>>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
    </form>

    <% } else { %>
    <p>データが見つかりません。</p>
    <% } %>

    <div id="second-lern">
        <form action="Resalelist">
            <input type="submit" value="リセール一覧" class="second-button">
        </form>
        <form action="Purchaselist">
            <input type="submit" value="購入済入場券" class="second-button">
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/black_out.js"></script>
</body>
</html>
