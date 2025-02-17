<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="facilityheader.jsp" %>

<form action="FullInfoChangeComplete">
<p>営業時間：${fac_info.open_time}～${fac_info.close_time}</p>
<p>販売開始日：${fac_info.sls_str}日前</p>
<p>各タイムスロットの上限人数：${fac_info.max_num}</p>
<p>定休日:${hol_list.size() == 0 ? "年中無休": hol_list}</p>
<p>価格範囲：${fac_info.low_price}～${fac_info.high_price}</p>
<p>初期価格：${fac_info.init_price}</p>
<p>当日券価格：${fac_info.sd_tkt_price}</p>
<p>子供割引：${fac_info.chld_dsc}%引き</p>
<p>カテゴリー：${fac_info.category == 0 ? "温泉" :fac_info.category == 1 ? "寺・寺院" :fac_info.category == 2 ? "博物館" :fac_info.category == 3 ? "スキー場" :fac_info.category == 4 ? "水族館" :fac_info.category == 5 ? "動物園" :fac_info.category == 6 ? "果樹園" :fac_info.category == 7 ? "レジャー" :"その他"}</p>
<button>変更</button>
</form>
<form id="backbutton" action="FullInfoChangeDisplay">
<button>戻る</button>
</form>
