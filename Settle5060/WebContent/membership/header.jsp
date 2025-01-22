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
	<div class="header" id="header-color">
		<ul>
			<div class="cust__wrapper">
				<li class="nav__item"><a href="Inquiry">お問い合わせ</a></li>
				<li class="nav__item"><a href="MemberInformation">会員情報</a></li>
				<li class="nav__item"><a href="#" id="logout">ログアウト</a></li> <!-- IDを追加 -->
			</div>
			<div class="header-logo">
				<li class="nav__item"><a href="./categorySelect.jsp" class="head-settle">SETTLE</a></li>
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
    $(function(){
    var hour = new Date().getHours();
	console.log(hour);
    if(7<=hour && hour<17){
    	$('#header-color').css({
  				'background': '-moz-linear-gradient(top, #59b9c6, 	#007DC5)',
  	  			'background': '-webkit-linear-gradient(top, #59b9c6, #007DC5)',
  	  			'background': 'linear-gradient(to bottom, #59b9c6, 	#007DC5)'
    		});
    }else if(19<=hour && hour<=23 || 0<=hour && hour<6){
    	$('#header-color').css({
			'background': '-moz-linear-gradient(top, #465DAA, 	#283446)',
  			'background': '-webkit-linear-gradient(top, #465DAA, 	#283446)',
  			'background': 'linear-gradient(to bottom, #465DAA, 	#283446)'
		});
    }else if(6<=hour && hour<7 || 17<=hour && hour<19){
    	$('#header-color').css({
    		'background': '-moz-linear-gradient(top, #f8b500, 	#e14024)',
  			'background': '-webkit-linear-gradient(top, #f8b500, 	#e14024)',
  			'background': 'linear-gradient(to bottom, #f8b500, 	#e14024)'
		});
   	 }
    });
</script>
</body>
</html>
