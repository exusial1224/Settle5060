var loginError = "${loginError}";
if (loginError) {
var elements = document.querySelectorAll('input');
	elements.forEach(function(element) {
		element.classList.add('input-error');
	});
}
