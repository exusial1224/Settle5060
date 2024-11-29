
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="../css/facilitylogin.css">
</head>
<body>
<script type="text/javascript">
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
</script>
<div class="container">
    <!--  <h1 class="login-title">-->
    <form action="facilityLogin" method="post">
		<div class="group">
    		<input name="mail" type="email" id="user-id"><span class="highlight"></span><span class="bar"></span>
    		<label>メールアドレス</label>
  		</div>
        <div class="group">
    		<input name="password" type="password" id="password" required><span class="highlight"></span><span class="bar"></span>
    		<label>パスワード</label>
  		</div>
        <a href="PasswordResetMailFormDisplay" class="pass_link">▶新規登録またはパスワードを忘れた場合はこちら</a>
        <input type="submit" value="ログイン" class="login-input-btn">
    </form>
</div>

</body>
</html>
