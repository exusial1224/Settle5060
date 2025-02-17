<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  <%@ include file="facilityheader.jsp" %>-->

<form action="FullInfoChange">
<p>営業時間：<label>開始時間<input type="time" name="open_time" value="${fac_info.open_time}" required></label>～<label>終了時間<input type="time" name="close_time" value="${fac_info.close_time}" required></label></p>
<p>販売開始日：<label>販売開始日<input type="number" name="sls_str" value="${fac_info.sls_str}" required></label>日前</p>
<p>各タイムスロットの上限人数：<label>上限人数<input type="number" name="max_num" value="${fac_info.max_num}" required></label></p>
<p>定休日:<label><input type="checkbox" name="rg_hol" value="0" id="hol0">日</label><label><input type="checkbox" name="rg_hol" value="1" id="hol1">月</label><label><input type="checkbox" name="rg_hol" value="2" id="hol2">火</label><label><input type="checkbox" name="rg_hol" value="3" id="hol3">水</label><label><input type="checkbox" name="rg_hol" value="4" id="hol4">木</label><label><input type="checkbox" name="rg_hol" value="5" id="hol5">金</label><label><input type="checkbox" name="rg_hol"value="6" id="hol6">土</label></p>
<p>価格範囲：<label>最低価格<input type="number" name="low_price" id="low_price" value="${fac_info.low_price}" required></label>～<label>最高価格<input type="number" name="high_price" value="${fac_info.high_price}" required></label></p>
<p>初期価格：<label>最低価格<input type="number" name="init_price" value="${fac_info.init_price}" required></label></p>
<p>当日券価格：<label>当日券価格<input type="number" name="sd_tkt_price" value="${fac_info.sd_tkt_price}" required></label></p>
<p>子供割引：<label>割引率<input type="number" name="chld_dsc" max=100 value="${fac_info.chld_dsc}" required></label>%引き</p>
<p>カテゴリー：<select name="category">
<option value="0" id="category0">温泉</option>
<option value="1" id="category1">寺・寺院</option>
<option value="2" id="category2">博物館</option>
<option value="3" id="category3">スキー場</option>
<option value="4" id="category4">水族館</option>
<option value="5" id="category5">動物園</option>
<option value="6" id="category6">果樹園</option>
<option value="7" id="category7">レジャー</option>
<option value="8" id="category8">その他</option>
</select>
<button>変更</button>
</form>
<script>
window.onload = function(){
	var hol_num_list = ${hol_num_list}
	for(let i = 0; i < hol_num_list.length; i++){
		console.log(hol_num_list[i]);
		document.getElementById("hol"+ hol_num_list[i]).checked = true;
	}
	document.getElementById("category"+${fac_info.category}).selected = true;
	document.getElementById("low_price").max = ${fac_info.high_price}
}

</script>