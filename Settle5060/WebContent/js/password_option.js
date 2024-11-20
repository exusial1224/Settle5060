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


var showPasswordButton = document.getElementByClassName("showPasswordButton");
showPasswordButton.addEventListener("click", togglePasswordVisibility);

function togglePasswordVisibility() {
  var passwordInput = document.getElementById("password");
  if (passwordInput.type === "password") {
    passwordInput.type = "text";
    showPasswordButton.textContent = "非表示";
  } else {
    passwordInput.type = "password";
    showPasswordButton.textContent = "表示";
  }
}
