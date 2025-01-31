<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <%@ include file="facilityheader.jsp" %>　 -->

<%-- start======================== アラート表示用隠し要素 ========================start --%>
<input type="hidden" id="isTicketed" name="isTicketed" value="${test != null}">
<c:if test="${test != null}">
<div id="blackout" style="display: none;"></div>
<div id="popup" style="display: none;">
    <h2 class="popup-title">日付を選択</h2>
    <div class="popup-content">
        <%-- 購入者名、スロット情報、購入枚数を表示 --%>
        <ul>
        	<li>購入者名：${ticket.mbr_name}様</li>
        	<li>タイムスロット：${ticket.start_time}～${ticket.end_time}</li>
        	<li>購入枚数：${ticket.number}</li>
        </ul>
    </div>
    <div class="popup-buttons">
        <button id="popupSubmit">確定</button>
        <button id="popupCancel">キャンセル</button>
    </div>
</div>
</c:if>
<%-- end======================== アラート表示用隠し要素 ========================end --%>

<div class="contair">
<%-- start======================== システムメッセージ ========================start --%>
<c:if test="${message != null}">
	<div>${message}</div>
</c:if>
<%-- end======================== システムメッセージ ========================end --%>

<%-- start======================== 当日券の購入Form ========================start --%>
<div>
	<h2>当日入場券</h2>
	<form name="BuySameDayTicketForm" action="BuySameDayTicket" method="get">
		<label for="adultNum">大人</label>
	    <input type="number" name="adultNum" min="0" required/>
	    <label for="childNum">子供</label>
	    <input type="number" name="childNum" min="0" required/>
	    <button type="submit">購入</button>
	</form>
</div>
<%-- end======================== 当日券の購入Form ========================end --%>

<hr>

<%-- start======================== タイムスロット表示 ========================start --%>
<div>
	<h2>タイムスロット</h2>
	<ul>
	<c:forEach var="slot" items="${soltList}">
		<li>
			${slot.start_time} ～ ${slot.end_time}
			残り：${slot.remain} 人
		</li>
	</c:forEach>
	</ul>
</div>
<%-- end======================== タイムスロット表示 ========================end --%>

<hr>

<%-- start======================== 団体者表示 ========================start --%>
<form action="OrganizationCancel">
<div>
	<h2>団体来場者</h2>
	<c:forEach var="op" items="${opList}">
		<table>
			<tr><td>${op.org_name }様</td><td></td><td><input type="checkbox" id ="org_sl_id" name="org_sl_id" value="${op.org_pur_id}"></td></tr>
			<tr><td>${op.rep_name }様</td><td>時間${op.start_time}～</td></tr>
			<tr><td rowspan="2"><button onclick="orgEntry(${op.org_pur_id});" ${op.gr_tkt_admitted ? "disabled" : "" }>入場：${op.gr_tkt_admitted ? "済" : "未" }</button></td><td>枚数　大人${op.num_adlt_tkt_gr - op.cnc_gr_adlt}枚　子供${op.num_chld_tkt_gr - op.cnc_gr_chld}枚</td></tr>
			<tr><td>お電話番号${op.org_tel}</td></tr>
		</table>
	</c:forEach>
</div>
<button>団体購入キャンセル</button>
</form>
<%-- end======================== 団体者表示 ========================end --%>

<canvas id="canvas" hidden></canvas><%-- カメラ映像表示 --%>

</div><%-- contair --%>

<%-- start======================== 団体者用隠しForm ========================start --%>
<form action="OrgEntry" id="orgForm" method="get" name="orgForm">
	<input type="hidden" name="org_pur_id" value="">
</form>
<%-- end======================== 団体者用隠しForm ========================end --%>

<%-- start======================== QRコード読み取り時用隠しForm ========================start --%>
<form action="Entry" id="entryForm" method="get" name="entryForm">
	<input type="hidden" name="pur_id" value="">
</form>
<%-- end======================== 団体者用隠しForm ========================end --%>

<%-- ======================== JS読み込み ======================== --%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jsQR.js"></script>
<script type="text/javascript" src="../js/ticketEntry.js"></script>
<script type="text/javascript" src="../js/facilityEntry.js"></script>

</body>
</html>