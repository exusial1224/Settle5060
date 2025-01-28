
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="../css/generic.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
	<div class="container">
		<!--  <h1 class="login-title">-->
		<p class=title-text> SETTLEにログイン</p>
	    <hr>
	    <form action="facilityLogin" method="post" class="login-form">

	    	<p class="text-form"><label for="mail" class="login-label">メールアドレス：</label></p>
	    	<input name="mail" type="email" id="user-id" placeholder="✉Mail" class="input-form" required>

	    	<p class="text-form"><label for="password" class="login-label">パスワード：</label></p>
	    	<input name="password" type="password" id="password" placeholder="🔒Password" class="input-form" required>

			<c:if test ="${loginError != null}">
  		   		<div class="error-text">パスワードまたはメールアドレスが無効です。</div>
			</c:if>


        	<a href="#" class="pass_link" onclick="return showPopup();">▶新規登録またはパスワードを忘れた場合はこちら</a>
	        <p class="login-btn"><input type="submit" value="ログイン" class="login-input-btn"></p>

	    </form>
	</div>
	<script>
	    var loginError = "${loginError}";
	    if (loginError) {
	        var elements = document.querySelectorAll('input');
	        elements.forEach(function(element) {
	            element.classList.add('input-error');
	        });
	    }


	    $(window, document, undefined).ready(function() {

	        $('input').blur(function() {
	            var $this = $(this);
	            if ($this.val())
	                $this.addClass('used');
	            else
	                $this.removeClass('used');
	        });

	        var $ripples = $('.ripples');

	        $ripples.on('click.Ripples', function(e) {

	            var $this = $(this);
	            var $offset = $this.parent().offset();
	            var $circle = $this.find('.ripplesCircle');

	            var x = e.pageX - $offset.left;
	            var y = e.pageY - $offset.top;

	            $circle.css({
	                top: y + 'px',
	                left: x + 'px'
	            });

	            $this.addClass('is-active');

	        });

	        $ripples.on('animationend webkitAnimationEnd mozAnimationEnd oanimationend MSAnimationEnd', function(e) {
	            $(this).removeClass('is-active');
	        });

	    });

	    // ポップアップを表示する関数
	    function showPopup() {
	        alert("カスタマーサポートへお問い合わせください。\n電話番号: 00-0000-0000\n受付時間: 12:00～18:00");
	        return false; // リンクのデフォルトの動作を防ぐ
	    }
	</script>

</body>
</html>
