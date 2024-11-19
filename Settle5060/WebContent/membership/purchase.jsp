<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
</head>
<body>
<%
    // セッションから選択されたスロットを取得
    bean.Slot selectedSlot = (bean.Slot) session.getAttribute("selectedSlot");
    if (selectedSlot != null) {
%>
    <h3>選択した施設名: <%= session.getAttribute("selectedFacilityName") %></h3>
    <h3>選択した時間帯: <%= selectedSlot.getStart_time() %> ～ <%= selectedSlot.getEnd_time() %></h3>
    <form action="ConfirmReservation" method="post">
        <table>
            <tr>
                <td>枚数 (大人):</td>
                <td><input type="number" name="adultTickets" min="0" required></td>
            </tr>
            <tr>
                <td>枚数 (小人):</td>
                <td><input type="number" name="childTickets" min="0" required></td>
            </tr>
        </table>
        <input type="submit" value="次へ">
    </form>
<%
    } else {
%>
    <p>選択したスロット情報が見つかりません。</p>
<%
    }
%>

</body>
</html>
