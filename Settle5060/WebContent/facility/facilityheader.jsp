<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/facilityheader.css">
<link rel="stylesheet" href="../css/facility_entry.css">
<title>SETTLE施設管理</title>
  <!-- jQueryのCDN -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<header class="faci-header">
		<div class="header-logo">
			<a href="facilityTop.jsp">SETTLE施設管理</a>
		</div>
		<a href="#" id="logout" class="logout-botton">ログアウト<i class="fas fa-angle-down fa-position-bottom"></i></a>


</header>
<script>
    $(document).ready(function() {
        $('#logout').click(function(event) {
            event.preventDefault(); // デフォルトのリンク動作を無効化
            if (confirm("ログアウトしますか？")) {
                // ユーザーが「OK」を押した場合、ログアウト処理のリンク先に遷移
                window.location.href = "Logout";
            }
        });
    });
</script>