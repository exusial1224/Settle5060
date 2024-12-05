<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <link rel="stylesheet" type="text/css" href="../css/top.css">
    <meta charset="UTF-8">
    <title>入場券購入</title>
    <style>
        /* ポップアップ関連のスタイル */
        #blackout {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 999;
        }
        #popup {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }
        .popup-buttons button {
            margin: 5px;
        }
    </style>
</head>
<body>

<div id="blackout" style="display: none;"></div>
<div id="popup" style="display: none;">
    <h2 class="popup-title">日付を選択</h2>
    <div class="popup-content">
        <select id="popupDateSelect" name="popupDateSelect">
            <option value="">--日付を選択--</option>
            <%-- サーバーサイドから日付リストを取得して埋め込み --%>
            <%
                java.util.List<String> availableDates = (java.util.List<String>) session.getAttribute("availableDates");
                if (availableDates != null) {
                    for (String date : availableDates) {
            %>
            <option value="<%= date %>"><%= date %></option>
            <%
                    }
                }
            %>
        </select>
    </div>
    <div class="popup-buttons">
        <button id="popupSubmit">確定</button>
        <button id="popupCancel">キャンセル</button>
    </div>
</div>

<div class="container">
    <div class="title-text">入場券購入</div>
    <hr>
    <div id="first-lern">
        <h1 id="fac-name">
            ${facilityName}
        </h1>
        <button onclick="location.href='ChangeFacility'" class="change-btn">選択施設を変更</button>
        <form id="dateForm" action="DateSelect" method="post">
            <label for="dateInput">日付を選択</label>
            <input id="dateInput" type="text" name="selectedDate" readonly placeholder="日付を選択" onclick="showPopup()" />
            <button type="submit">選択</button>
        </form>
    </div>

    <%-- 時間帯選択フォーム --%>
    <%
        java.util.List<bean.Slot> timeSlots = (java.util.List<bean.Slot>) session.getAttribute("timeSlots");
        String selectedDateStr = (String) session.getAttribute("selectedDate");
        java.sql.Date selectedDate = null;
        if (selectedDateStr != null) {
            selectedDate = java.sql.Date.valueOf(selectedDateStr);
        }
        if (timeSlots != null && selectedDate != null) {
    %>
    <form action="SlotSelect" method="post">
        <h4>時間帯を選択してください</h4>
        <div class="sel-slot-scroll">
            <table border="1" id="sel-slot-table">
                <tr>
                    <th class="sticky">時間帯</th>
                    <th class="sticky">現在の価格</th>
                    <th class="sticky">残り枚数</th>
                    <th class="sticky">リセール予約</th>
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
<script>
    function showPopup() {
        $("#blackout").fadeIn();
        $("#popup").fadeIn();
    }

    function closePopup() {
        $("#popup").fadeOut();
        $("#blackout").fadeOut();
    }

    $(document).ready(function () {
        $("#popupCancel").click(function () {
            closePopup();
        });

        $("#popupSubmit").click(function () {
            const selectedDate = $("#popupDateSelect").val();
            if (selectedDate) {
                $("#dateInput").val(selectedDate); // 入力フィールドに反映
                closePopup();
            } else {
                alert("日付を選択してください。");
            }
        });
    });
</script>
</body>
</html>
