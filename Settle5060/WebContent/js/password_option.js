// パスワード比較
function validatePassword() {
	    var password = document.getElementById('password');
	    var confirmPassword = document.getElementById('confirmPassword');

	    if (password.value !== confirmPassword.value) {
	      confirmPassword.setCustomValidity('パスワードが一致しません。');
	    } else {
	      confirmPassword.setCustomValidity('');
	    }
	  }

	  window.onload = function() {
	    document.getElementById('password').onchange = validatePassword;
	    document.getElementById('confirmPassword').onkeyup = validatePassword;
	  }

/*
 * パスワードの表示非表示切り替え
 * ClassNameで取得したいので後で修正
 * */
var showPasswordButton = document.getElementById("showPasswordButton");
showPasswordButton.addEventListener("click", togglePasswordVisibility);

function togglePasswordVisibility() {
  var passwordInput = document.getElementById("password");
  if (passwordInput.type === "password") {
    passwordInput.type = "text";
    document.getElementById("confirmPassword").type = "text";
    document.getElementById("oldPassword").type = "text";
    showPasswordButton.textContent = "非表示";
  } else {
    passwordInput.type = "password";
    document.getElementById("confirmPassword").type = "password";
    document.getElementById("oldPassword").type = "password";
    showPasswordButton.textContent = "表示";
  }
}
