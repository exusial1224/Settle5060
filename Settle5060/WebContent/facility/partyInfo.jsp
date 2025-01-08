<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="facilityheader.jsp" %>
<input type="date" id="party_visit" name="party_visit">
<div id="datetime-list">
</div>

</body>
<!-- あとでJSファイルに移すか確認 -->
    <script>
    const today = new Date();
    function dateFormat(today, format){
       format = format.replace("YYYY", today.getFullYear());
       format = format.replace("MM", ("0"+(today.getMonth() + 1)).slice(-2));
       format = format.replace("DD", ("0"+ today.getDate()).slice(-2));
       return format;
    }
    const data = dateFormat(today,'YYYY-MM-DD');
    const field = document.getElementById("party_visit");
    field.value = data;
    field.setAttribute("min", data);
    </script>
</html>