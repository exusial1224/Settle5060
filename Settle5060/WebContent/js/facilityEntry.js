function orgEntry(orgId){
	var form1 = document.getElementById('orgForm');
	form1.org_pur_id.value = orgId;
	form1.submit();
}

//ポップアップイン
function showPopup() {
    $("#modal")[0].style.display = 'block';
    $("#popup")[0].style.display = 'block';
}

//ポップアップアウト
function hidePopup() {
    $("#modal")[0].style.display = 'none';
    $("#popup")[0].style.display = 'none';
}

window.addEventListener('click', function(event) {
	  if (event.target === document.getElementById('modal')) {
	    hidePopup();
	  }
	});

//読み込み時、ポップアップの判定
$(document).ready(()=>{
	if($("#isTicketed")[0].value){
		showPopup();
	}
});
