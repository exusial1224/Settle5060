<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/header.css">
    <link rel="stylesheet" href="../css/footer.css">
    <title>SETTLE</title>
    <!-- jQueryのCDN -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<header class="cust-header">
	<div class="header">
		<ul>
			<div class="cust__wrapper">
				<li class="nav__item"><a href="Inquiry">お問い合わせ</a></li>
				<li class="nav__item"><a href="MemberInformation">会員情報</a></li>
				<li class="nav__item"><a href="#" id="logout">ログアウト</a></li> <!-- IDを追加 -->
			</div>
			<div class="header-logo">
				<li class="nav__item"><a href="./top.jsp">SETTLE</a></li>
			</div>
		</ul>
	</div>
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
</body>
</html>
